package com.example.chatapp.controller;

import com.example.chatapp.model.ChatException;
import com.example.chatapp.model.entity.Login;
import com.example.chatapp.model.entity.User;
import com.example.chatapp.model.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public User Login(@RequestBody Login user) throws ChatException {
        System.out.println("  "+ user);
        try {
            return loginService.login(user.getUsername(), user.getPassword());
        } catch (ChatException e) {
            throw new ChatException("user name or password is invalid.");
        }
//        try {
//            User loginUser = loginService.login(user.getUsername(), user.getPassword());
//            return "Login Success!";
//        } catch (ChatException e) {
//            return null;
//        }
    }
}
