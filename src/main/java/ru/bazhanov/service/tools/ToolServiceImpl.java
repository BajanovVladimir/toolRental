package ru.bazhanov.service.tools;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bazhanov.dto.ToolDTO;
import ru.bazhanov.model.*;
import ru.bazhanov.repository.AddressRepository;
import ru.bazhanov.repository.PersonRepository;
import ru.bazhanov.repository.ToolRepository;
import ru.bazhanov.service.orders.OrderService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ToolServiceImpl implements ToolService{
    private final AddressRepository addressRepository;
    private final ToolRepository toolRepository;
    private final PersonRepository personRepository;
    private final OrderService orderService;

    @Autowired
    public ToolServiceImpl(AddressRepository addressRepository, ToolRepository toolRepository, PersonRepository personRepository,OrderService orderService){
        this.addressRepository = addressRepository;
        this.toolRepository = toolRepository;
        this.personRepository = personRepository;
        this.orderService = orderService;
    }
    @Override
    public Boolean save(ToolDTO tool, User user) {
        Double doublePrice = 0.0;
        Address newAddress =  addressRepository.save(new Address(tool.getDistrict(),tool.getStreet()));
        int id = newAddress.getId();
        if(newAddress == null){
            return false;
        }
        try{
            String toolPrice = tool.getPrice();
            doublePrice = Double.parseDouble(toolPrice);
        } catch (NumberFormatException | NullPointerException e){
            return false;
        }
        Person thisPerson = personRepository.findByUser(user);

        Tool newTool = new Tool(tool.getName(),tool.getSpecifications(),thisPerson,newAddress,doublePrice);
        if(toolRepository.save(newTool) == null) {
            return false;
        }
        return true;
    }
    @Override
    public List<Tool> findAllByUser(User user){
        Person person = personRepository.findByUser(user);
        return toolRepository.findByPerson(person);
    }
    @Override
    public void deleteById(int toolId) {
        if(getCurrentOrdersByToolId(toolId).isEmpty()){
            toolRepository.deleteById(toolId);
        } else {
            throw new RuntimeException();
        }
    }

    private List<Order> getCurrentOrdersByToolId(int toolId){
        Tool tool = toolRepository.getReferenceById(toolId);
        return tool.getOrders().stream()
                               .filter(o -> (o.getStopped() == false) && (o.getCompleted() == false))
                               .collect(Collectors.toList());
    }

    @Override
    public List<Tool> findAll(){
       return toolRepository.findAll();
    }

    @Override
    public List<Tool> findByName(String name){
        return toolRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Tool> findByName(List<Tool> tools,String name){
        return tools.stream().filter(t -> StringUtils.containsIgnoreCase(t.getName(), name))
                             .collect(Collectors.toList());
    }
    @Override
    public List<Tool> findByPriceBetween(Double priceMin, Double priceMax) {
        return toolRepository.findByPriceBetween(priceMin,priceMax);
    }

    @Override
    public List<Tool> findByPriceBetween(List<Tool> tools, Double priceMin, Double priceMax) {
        Double minPrice;
        Double maxPrice;
        if(priceMin>priceMax){
            maxPrice = priceMin;
            minPrice = priceMax;
        }else{
            minPrice = priceMin;
            maxPrice = priceMax;
        }
        return  tools.stream().filter(t -> (t.getPrice() >= minPrice
                && t.getPrice() <= maxPrice)).sorted(Comparator.comparing(Tool::getPrice)).toList();
    }

    @Override
    public Double findMaxPrice() {
        return toolRepository.findMaxPrice();
    }

    @Override
    public List<Tool> findToolsByDates(LocalDate startDate, LocalDate stopDate) {
        List<Order> ordersByDateBetween = orderService.getCurrentOrdersByDates(startDate,stopDate);
        List<Tool> allTools = toolRepository.findAll();
        Set<Tool> orderTools = ordersByDateBetween.stream().map(Order::getTool).collect(Collectors.toSet());
        allTools.removeAll(orderTools);
        return allTools.stream().sorted(Comparator.comparing(Tool::getName)).toList() ;
    }


    @Override
    public List<Tool> findToolsByDistrict(List<Tool> tools,String district) {
        return tools.stream().filter(t -> StringUtils.containsIgnoreCase(t.getDistrict(), district)).sorted(Comparator.comparing(Tool::getName))
                             .collect(Collectors.toList());
    }

    @Override
    public Tool getToolById(int id) {
        return toolRepository.getReferenceById(id);
    }

    @Override
    public List<Order> getOrdersByTools(List<Tool> tools) {
        return tools.stream().map(Tool::getOrders)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
    }

    @Override
    public  List<Order> getStoppedOrdersByTools(List<Tool> tool){
        return getOrdersByTools(tool).stream().filter(o -> o.getStopped() == true)
                                              .collect(Collectors.toList());
    }

    @Override
    public List<Order> getCurrentOrdersByTools(List<Tool> tools) {
        return getOrdersByTools(tools).stream()
                                      .filter(o -> (o.getStopped() == false) && (o.getCompleted() == false))
                                      .collect(Collectors.toList());
    }

    public Boolean toolIsFree(Tool tool,LocalDate startDate, LocalDate stopDate){
        List<Order> orders = orderService.getCurrentOrdersByDates(startDate,stopDate);
        Set<Tool> orderTools = orders.stream().map(Order::getTool).collect(Collectors.toSet());
        if(orderTools.contains(tool)){
            return  false;
        }
        return true;
    }
}
