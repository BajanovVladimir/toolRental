package ru.bazhanov.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.bazhanov.UserRegistrationDTO;
import ru.bazhanov.service.user.UserService;


@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;
    @ModelAttribute("userForm")
    public UserRegistrationDTO userRegistrationDto() {
        return new UserRegistrationDTO();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public ModelAndView registerUserAccount(@ModelAttribute("userForm") UserRegistrationDTO registrationDto) {
        ModelAndView mv;
        if(!userService.save(registrationDto)){
            mv = new ModelAndView("registration");
            mv.addObject("error", "Пользователь с таким именем уже существует");
            return mv;
        }
        mv = new ModelAndView("login");
        return mv;
    }
}
