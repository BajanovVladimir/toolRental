package ru.bazhanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.bazhanov.model.Order;
import ru.bazhanov.model.Person;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByPerson(Person person);
    List<Order> findByStopped(Boolean stop);
    @Query("SELECT o FROM Order o WHERE (o.startDate  BETWEEN ?1 and ?2) OR (o.stopDate  BETWEEN ?1 and ?2)")
    List<Order> findOrdersByDateBetween(LocalDate startDate,LocalDate stopDate);
}
