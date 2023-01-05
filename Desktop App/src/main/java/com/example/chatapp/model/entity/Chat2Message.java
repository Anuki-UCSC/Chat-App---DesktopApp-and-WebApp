package com.example.chatapp.model.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Chat2Message")
@Data
public class Chat2Message extends Message{
    private MessageBody.Group group = MessageBody.Group.chat2;

}
