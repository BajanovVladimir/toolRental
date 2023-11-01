package ru.bazhanov.service.orders;

import ru.bazhanov.dto.OrderDTO;
import ru.bazhanov.model.Order;
import ru.bazhanov.model.Person;
import ru.bazhanov.model.Tool;
import java.time.LocalDate;
import java.util.List;


public interface OrderService {

    Boolean save(Order order);
    Boolean save(Person person, Tool tool, LocalDate startDate, LocalDate stopDate);
    Boolean save(Person person, OrderDTO orderDTO);
    List<Order> findByPerson(Person person);
    Order findOrderById(int id);
    List<Order> findStoppedByPerson(Person person);
    List<Order> findCurrentByPerson(Person person);
    List<Order> getCurrentOrdersByDates(LocalDate startDate, LocalDate stopDate);
}
