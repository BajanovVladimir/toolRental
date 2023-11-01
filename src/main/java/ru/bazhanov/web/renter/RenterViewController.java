package ru.bazhanov.web.renter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.bazhanov.model.Person;
import ru.bazhanov.model.User;
import ru.bazhanov.repository.PersonRepository;
import ru.bazhanov.service.orders.OrderService;


@Controller
@RequestMapping("/renter/renterView")
public class RenterViewController {

    private final PersonRepository personRepository;
    private final OrderService orderService;

    @Autowired
    RenterViewController(PersonRepository personRepository,OrderService orderService){
        this.personRepository = personRepository;
        this.orderService = orderService;
    }

    @GetMapping
    public ModelAndView showRenterView() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        Person personUser = personRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("renter/renterView");
        mv.addObject("personName",personUser.getName());
        mv.addObject("orders", orderService.findCurrentByPerson(personUser));
        mv.addObject("stoppedOrders",orderService.findStoppedByPerson(personUser));
        return  mv;
    }
}
