package ru.bazhanov.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.bazhanov.model.Address;


public interface AddressRepository extends JpaRepository<Address, Integer> {
}
