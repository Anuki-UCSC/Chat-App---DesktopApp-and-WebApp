package com.example.chatapp.model.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
public class Message {

    @Id
    private ObjectId id;
    private String message;
    private String sender;
    private MessageBody.Type type;
    private Date createdTime = new Date();
}
