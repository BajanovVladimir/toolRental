package ru.bazhanov.service.tools;

import ru.bazhanov.dto.ToolDTO;
import ru.bazhanov.model.Order;
import ru.bazhanov.model.Tool;
import ru.bazhanov.model.User;
import java.time.LocalDate;
import java.util.List;

public interface ToolService {
    Boolean save(ToolDTO tool, User user);
    List<Tool> findAllByUser(User user);
    void deleteById(int toolId);
    List<Tool> findAll();
    List<Tool> findByName(String name);
    public List<Tool> findByName(List<Tool> tools,String name);
    List<Tool> findByPriceBetween(Double priceMin, Double priceMax);
    List<Tool> findByPriceBetween(List<Tool> tools,Double priceMin, Double priceMax);
    Double findMaxPrice();
    List<Tool> findToolsByDates(LocalDate startDate, LocalDate stopDate);
    List<Tool> findToolsByDistrict(List<Tool> tools,String district);
    Tool getToolById(int id);
    List<Order> getOrdersByTools(List<Tool> tools);
    List<Order> getStoppedOrdersByTools(List<Tool> tools);
    List<Order> getCurrentOrdersByTools(List<Tool> tools);
    Boolean toolIsFree(Tool tool,LocalDate startDate, LocalDate stopDate);

}
