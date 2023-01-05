package com.example.chatapp.model.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Chat1Message")
@Data
public class Chat1Message extends Message{
    private MessageBody.Group group = MessageBody.Group.chat1;


}
