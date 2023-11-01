package ru.bazhanov.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.bazhanov.dto.UserRegistrationDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class RegistrationControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void testGet() throws Exception {
        String url = "/registration";
        this.mockMvc.perform(get(url))
                .andExpect(status().isOk());
    }


    @Test
    void testPost() throws Exception {
       String url = "/registration";
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Andrey")
                .param("phone","+79874564321")
                .param("login","userDTO")
                .param("password","userDTO")
                .sessionAttr("userForm", new UserRegistrationDTO())
        )
                .andExpect(status().isOk());
    }
}
