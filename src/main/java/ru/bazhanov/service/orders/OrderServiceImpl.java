package ru.bazhanov.service.orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bazhanov.dto.OrderDTO;
import ru.bazhanov.model.Order;
import ru.bazhanov.model.Person;
import ru.bazhanov.model.Tool;
import ru.bazhanov.repository.OrderRepository;
import ru.bazhanov.repository.ToolRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    private final ToolRepository toolRepository;

    @Autowired
    OrderServiceImpl(OrderRepository orderRepository, ToolRepository toolRepository){
        this.orderRepository = orderRepository;
        this.toolRepository = toolRepository;
    }
    @Override
    public Boolean save(Order order) {
        try{
            orderRepository.save(order);
            return true;
        }catch (NumberFormatException | NullPointerException e){
            return false;
        }
    }

    @Override
    public Boolean save(Person person, Tool tool, LocalDate startDate, LocalDate stopDate) {
        try{
            Order newOrder = new Order(person,tool,startDate,stopDate);
            if(orderRepository.save(newOrder).equals( null)){
                return true;
            }
            return false;
        }catch (NumberFormatException | NullPointerException e){
            return false;
        }
    }

    @Override
    public Boolean save(Person person, OrderDTO orderDTO) {
        Tool tool = toolRepository.getReferenceById(orderDTO.getToolId());
       return this.save(person,tool,orderDTO.getStartDate(),orderDTO.getStopDate());
    }

    @Override
    public Order findOrderById(int id){
        return orderRepository.getReferenceById(id);
    }

    @Override
    public List<Order> findByPerson(Person person) {
        return orderRepository.findByPerson(person);
    }
    @Override
    public List<Order> findStoppedByPerson(Person person){
        return findByPerson(person).stream().filter(o -> o.getStopped() == true).collect(Collectors.toList());
    }

    @Override
    public List<Order> findCurrentByPerson(Person person){
        return findByPerson(person).stream().filter(o -> (o.getStopped() == false) && (o.getCompleted() == false)).collect(Collectors.toList());
    }
    @Override
    public List<Order> getCurrentOrdersByDates(LocalDate startDate, LocalDate stopDate){
            List<Order> ordersByDateBetween = orderRepository.findOrdersByDateBetween(startDate,stopDate);
            List<Order> stoppedOrders = orderRepository.findByStopped(true);
            ordersByDateBetween.removeAll(stoppedOrders);
            return  ordersByDateBetween;
    }
}
