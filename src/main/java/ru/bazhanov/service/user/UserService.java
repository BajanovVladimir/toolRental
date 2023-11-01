package ru.bazhanov.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.bazhanov.UserRegistrationDTO;
import ru.bazhanov.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    Boolean save(UserRegistrationDTO registrationDto);

    List<User> allUsers();
}
