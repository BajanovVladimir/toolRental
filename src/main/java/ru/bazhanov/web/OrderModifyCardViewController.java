package ru.bazhanov.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.bazhanov.model.Order;
import ru.bazhanov.model.Role;
import ru.bazhanov.model.User;
import ru.bazhanov.service.orders.OrderService;

import java.util.List;

@Controller
@RequestMapping("/orderModifyCardView")
public class OrderModifyCardViewController {

    private OrderService orderService;

    @Autowired
    OrderModifyCardViewController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public ModelAndView showOrderModifyCardView(@RequestParam(value = "orderId") int orderId){
        Order order = orderService.findOrderById(orderId);
        ModelAndView mv = new ModelAndView("/orderModifyCardView");
        mv.addObject("tool",order.getTool());
        mv.addObject("order",order);
        return mv;
    }

    @PostMapping
    public ModelAndView stopOrder(@RequestParam(value="orderId") int orderId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User();
        String redirect = "redirect:/renter/renterView";
        List<Role>  roles = (List<Role>) user.getRoles();
        String role = roles.get(0).getName();
        if(role.contains(Role.ROLE_LESSOR)){
            redirect = "redirect:/lessor/lessorView";
        }
        Order order = orderService.findOrderById(orderId);
        order.setStopped(true);
        orderService.save(order);

        ModelAndView mv = new ModelAndView(redirect);
        return mv;
    }
}
