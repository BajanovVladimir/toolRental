package ru.bazhanov.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bazhanov.model.Role;
import ru.bazhanov.model.User;
import ru.bazhanov.service.user.UserService;

import java.util.HashSet;
import java.util.Set;


@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Test
    public void test_that_context_is_ok(){
        Assertions.assertDoesNotThrow(() -> userRepository.findAll());
    }
    @Test
    public void test_that_user_find_by_id_is_ok(){
        User userFromDB = userRepository.findById(1).orElseThrow();
        Assertions.assertEquals("user1",userFromDB.getLogin());
    }
    @Test
    public void test_that_user_is_saved(){
        User user = new User("user3","password3");
        user.setId(3);
        Role role = roleRepository.findById(2).orElseThrow();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        User userFromDB = userRepository.findById(3).orElseThrow();
        Assertions.assertEquals("user3",userFromDB.getLogin());
    }
}
