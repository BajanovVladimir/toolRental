package ru.bazhanov.web.renter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.bazhanov.dto.OrderDTO;
import ru.bazhanov.model.Person;
import ru.bazhanov.model.Tool;
import ru.bazhanov.model.User;
import ru.bazhanov.repository.PersonRepository;
import ru.bazhanov.service.orders.OrderService;
import ru.bazhanov.service.tools.ToolService;

import java.time.LocalDate;

@Controller
@RequestMapping("/renter/orderCreateCard")
public class OrderCreateCardController {

    @Autowired
    private ToolService toolService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private OrderService orderService;


    @GetMapping
    public ModelAndView showOrderCardView(@RequestParam(value = "toolId") int toolId){
        ModelAndView mv = new ModelAndView("/renter/orderCreateCard");
        Tool tool = toolService.getToolById(toolId);
        mv.addObject("order",new OrderDTO());
        mv.addObject("tool",tool);
        mv.addObject("nowDate", LocalDate.now());
        return mv;
    }
    @PostMapping
    public ModelAndView createdOrder(@ModelAttribute("order") OrderDTO order){
        ModelAndView mv;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        Person personUser = personRepository.findByUser(user);
        Tool tool = toolService.getToolById(order.getToolId());
        if(toolService.toolIsFree(tool,order.getStartDate(),order.getStopDate())){
            orderService.save(personUser,tool,order.getStartDate(),order.getStopDate());
            mv = new ModelAndView("redirect:/renter/renterView");
        } else {
            mv = new ModelAndView("/renter/orderCreateCard");
            mv.addObject("message",true);
            mv.addObject("order",new OrderDTO());
            mv.addObject("tool",tool);
        }
        return mv;
    }

}
