package ru.bazhanov.web.renter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.bazhanov.model.Person;
import ru.bazhanov.model.Tool;
import ru.bazhanov.model.User;
import ru.bazhanov.repository.PersonRepository;
import ru.bazhanov.service.tools.ToolService;
import ru.bazhanov.utils.PriceUtils;
import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/renter/toolSearchView")
public class ToolSearchViewController {

    private final PersonRepository personRepository;
    private final ToolService toolService;


    @Autowired
    ToolSearchViewController(PersonRepository personRepository,ToolService toolService){
        this.personRepository = personRepository;
        this.toolService = toolService;
    }


    @GetMapping
    public ModelAndView showToolSearchView() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        Person personUser = personRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("renter/toolSearchView");
        mv.addObject("personName",personUser.getName());
        return  mv;
    }

    @PostMapping
    public ModelAndView findTool(@RequestParam(value =  "name") String name,
                                 @RequestParam(value = "district") String district,
                                 @RequestParam(value = "priceMin") String priceMin,
                                 @RequestParam(value = "priceMax") String priceMax,
                                 @RequestParam(value = "startDate")LocalDate startDate,
                                 @RequestParam(value = "stopDate") LocalDate stopDate){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        Person personUser = personRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("renter/toolSearchView");
        mv.addObject("personName",personUser.getName());
        Double maxPrice = toolService.findMaxPrice();
        Double priceMinDouble = PriceUtils.getDoubleFromString(priceMin,0.0);
        Double priceMaxDouble = PriceUtils.getDoubleFromString(priceMax,maxPrice);
        List<Tool> toolList = toolService.findToolsByDates(startDate,stopDate);

        if(!priceMax.equals("") || !priceMin.equals("")){
            toolList = toolService.findByPriceBetween(toolList,priceMinDouble,priceMaxDouble);
        }
        if(!name.equals("")){
           toolList = toolService.findByName(toolList,name);
        }
        if(!district.equals("")){
            toolList = toolService.findToolsByDistrict(toolList,district);

        }

        mv.addObject("tools",toolList);
        return mv;
    }
}
