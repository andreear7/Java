package com.music.music;
import com.music.music.controllers.UsersController;
import com.music.music.models.User;
import com.music.music.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class MusicApplicationTests {

    @Test
    void contextLoads() {
    }

    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private Model userModel;
    @Autowired
    UsersController controller;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void testLoginPage() {
        User user = new User();
        assertEquals("log-user", controller.showSignInForm(user));
    }

    @Test
    void testLogInOk() {
        User user = new User("g", "g");
        assertEquals("index", controller.logInUser(user, bindingResult, model, userModel));
    }

    @Test
    void testLogInNotOk() {
        User user = new User("gg", "g");
        assertEquals("log-user", controller.logInUser(user, bindingResult, model, userModel));
    }


    @Test
    public void testLoadTopPage() throws Exception {
        mockMvc.perform(get("/songs/top/{id}", 81)).andExpect(status().isOk());
    }

    @Test
    public void testLogOut() throws Exception {
        mockMvc.perform(get("/logout")).andExpect(view().name("log-user"));
    }

    @Test
    public void testTopContent() throws Exception {
        mockMvc.perform(get("/songs/top/{id}", 81)).andExpect(content().string(containsString("melodie")));
    }

}


