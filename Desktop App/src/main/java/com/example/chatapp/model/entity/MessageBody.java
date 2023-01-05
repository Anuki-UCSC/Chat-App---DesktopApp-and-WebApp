package com.example.chatapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MessageBody {

    private String message;
    private String sender;
    private Type type;
    private Group group;

    public enum Group{
        general , chat1 , chat2
    }

    public enum Type{
        normal , special
    }



}
