package com.music.music.controllers;
import com.music.music.models.User;
import com.music.music.repository.SongRepository;
import com.music.music.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller

public class UsersController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SongRepository songRepository;

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @GetMapping("/signin")
    public String showSignInForm(User user) {
        return "log-user";
    }

    @GetMapping("/logout")
    public String showSignForm(User user) {
        return "log-user";
    }

    @GetMapping("/home/{id}")
    public String showHome(@PathVariable int id, Model model, Model userModel) {
        model.addAttribute("songs", songRepository.findAll());
        userModel.addAttribute("user", userRepository.findById(id));
        return "index";
    }

    @PostMapping(path = "/adduser")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model, Model userModel) {
        if (bindingResult.hasErrors()) {
            return "add-user";
        }
        String parola = user.getParola();
        String encoded = new BCryptPasswordEncoder().encode(parola);
        user.setParola(encoded);
        userRepository.save(user);
        model.addAttribute("songs", songRepository.findAll());
        userModel.addAttribute("user", user);
        return "index";

    }

    @PostMapping(path = "/loginuser")
    public String logInUser(@Valid User user, BindingResult bindingResult, Model model, Model userModel) {
        if (bindingResult.hasErrors()) {
            return "log-user";
        }
        User user1 = userRepository.findUserByNume(user.getNume());
        if (user1 != null) {
            boolean match = new BCryptPasswordEncoder().matches(user.getParola(), user1.getParola());
            if (!match) {
                return "log-user";
            } else {
                model.addAttribute("songs", songRepository.findAll());
                userModel.addAttribute("user", userRepository.findUserByNume(user.getNume()));
                return "index";
            }
        }
        return "log-user";
    }

    @PostMapping("/delete/{id}")
    String deleteUser(@PathVariable int id) {
        userRepository.delete(userRepository.findById(id));
        return "delete-ok";
    }

}
