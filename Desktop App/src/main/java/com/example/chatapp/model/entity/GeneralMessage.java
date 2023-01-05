package com.example.chatapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "GeneralChat")
@Data
public class GeneralMessage extends Message{
    private MessageBody.Group group = MessageBody.Group.general;

}
