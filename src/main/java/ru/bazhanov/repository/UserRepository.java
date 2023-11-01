package ru.bazhanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bazhanov.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByLogin(String login);
}
