package com.example.chatapp.controller;

import com.example.chatapp.model.entity.User;
import com.example.chatapp.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin

public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getUser(){
        return service.findAll();
    }

    @GetMapping("/chatUsersList")
    public List<User> findChatUsersList(@RequestParam String chat){
        List<User> users = service.findChatUsersList(chat);
        return users;
    }

    @GetMapping("/notChatUsersList")
    public List<User> findNotChatUsersList(@RequestParam String chat){
        List<User> users = service.findNotChatUsersList(chat);
        return users;
    }

}
