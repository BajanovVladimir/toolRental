package ru.bazhanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.bazhanov.model.Person;
import ru.bazhanov.model.Tool;

import java.util.List;


public interface ToolRepository extends JpaRepository<Tool,Integer> {
      List<Tool> findByPerson(Person person);
      List<Tool> findByNameContainingIgnoreCase(String name);
      List<Tool> findByPriceBetween(Double priceMin,Double PriceMax);
      @Query("SELECT MAX(t.price) FROM Tool t")
      Double findMaxPrice();
}
