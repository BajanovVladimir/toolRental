package ru.bazhanov.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bazhanov.dto.UserRegistrationDTO;
import ru.bazhanov.model.Person;
import ru.bazhanov.model.User;
import ru.bazhanov.repository.PersonRepository;
import ru.bazhanov.service.user.UserService;

@SpringBootTest
public class UserRegistrationDTOTest {

    @Autowired
    private UserService userService;
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void test_that_user_and_person_saved_ok() {
        int usersSize = userService.allUsers().size();
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setName("Andrey");
        userRegistrationDTO.setPhone("+79874564321");
        userRegistrationDTO.setLogin("userDTO");
        userRegistrationDTO.setPassword("userDTO");
        userService.save(userRegistrationDTO);
        Assertions.assertEquals(usersSize+1,userService.allUsers().size());
        User user  = (User)userService.loadUserByUsername("userDTO");
        int id = user.getId();
        Person person = personRepository.findById(id).orElseThrow();
        Assertions.assertEquals("Andrey",person.getName());
    }
}
