package ru.bazhanov.web.lessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.bazhanov.dto.ToolDTO;
import ru.bazhanov.model.User;
import ru.bazhanov.repository.PersonRepository;
import ru.bazhanov.service.tools.ToolService;


@Controller
@RequestMapping("/lessor/toolCreatedView")
public class ToolCreatedController {

    @Autowired
    ToolService toolService;
    @Autowired
    PersonRepository personRepository;

    @ModelAttribute("toolForm")
    private ToolDTO toolDTO(){
        return new ToolDTO();
    }

    @GetMapping
    public String showToolCreatedView(){
        return "lessor/toolCreatedView";
    }

    @PostMapping
    public ModelAndView addTool(@ModelAttribute("toolForm") ToolDTO toolDTO){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        toolService.save(toolDTO,user);
        ModelAndView mv = new ModelAndView("redirect:/lessor/lessorView");
       return  mv;
    }
}
