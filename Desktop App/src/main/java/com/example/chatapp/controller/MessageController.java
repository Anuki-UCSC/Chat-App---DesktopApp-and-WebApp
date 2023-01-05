package com.example.chatapp.controller;


import com.example.chatapp.model.entity.MessageBody;
import com.example.chatapp.model.service.MessageService;
import com.example.chatapp.utils.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private EventHandler eventHandler;

    @GetMapping
    public List getMessages(@RequestParam String chat){
        List messageList = messageService.getMessages(MessageBody.Group.valueOf(chat));
        System.out.println(messageList);
        return messageList;
    }


    // same endpoint for sending messages, add user, remove user functionalities
    @PostMapping
    public String sendMessage(@RequestBody MessageBody messageBody){
        eventHandler.sendMessage(messageBody);
        return "message sent.";
    }
}
