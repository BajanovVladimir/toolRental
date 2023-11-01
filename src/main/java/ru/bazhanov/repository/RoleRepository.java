package ru.bazhanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bazhanov.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
