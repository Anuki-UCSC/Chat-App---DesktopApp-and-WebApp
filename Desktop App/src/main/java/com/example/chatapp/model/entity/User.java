package com.example.chatapp.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String username;
    private String name;
    private Role role;
    private boolean chat1;
    private boolean chat2;

    private String password;


    public enum Role{
        Admin, GeneralUser
    }









    //    public String toString() {
//        return name;
//    }
//
//    public User() {
//    }
//
//    public User(String loginId) {
//        this.loginId = loginId;
//    }
}
