package ru.bazhanov.web.lessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.bazhanov.model.Order;
import ru.bazhanov.model.Person;
import ru.bazhanov.model.Tool;
import ru.bazhanov.model.User;
import ru.bazhanov.repository.PersonRepository;
import ru.bazhanov.service.tools.ToolService;
import java.util.List;



@Controller
@RequestMapping("/lessor/lessorView")
public class LessorViewController {


    private final PersonRepository personRepository;
    private final ToolService toolService;

    @Autowired
    LessorViewController(PersonRepository personRepository,ToolService toolService){
        this.personRepository = personRepository;
        this.toolService = toolService;
    }
    @GetMapping
    public ModelAndView showLessorView() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User();
        Person personUser = personRepository.findByUser(user);
        List<Tool> userTools = toolService.findAllByUser(user);
        List<Order> userOrders = toolService.getCurrentOrdersByTools(userTools);
        List<Order> userStoppedOrders = toolService.getStoppedOrdersByTools(userTools);
        ModelAndView mv = new ModelAndView("lessor/lessorView");
        mv.addObject("personName", personUser.getName());
        mv.addObject("tools", userTools);
        mv.addObject("orders",userOrders);
        mv.addObject("stoppedOrders",userStoppedOrders);
        return mv;
    }

}
