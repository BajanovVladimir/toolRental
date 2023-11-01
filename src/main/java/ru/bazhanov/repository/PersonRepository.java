package ru.bazhanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bazhanov.model.Person;
import ru.bazhanov.model.User;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByUser(User user);
}
