package com.example.chatapp.model.service;

import com.example.chatapp.model.ChatException;
import com.example.chatapp.model.entity.User;
import com.example.chatapp.model.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public List<User> findAll(){
        return userRepo.findAll();
    }


    public void updateUserChat(String username,String chat){
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if(optionalUser.isPresent()){
            if(chat.equals("chat1")){
                optionalUser.get().setChat1(!optionalUser.get().isChat1());
            }else{
                optionalUser.get().setChat2(!optionalUser.get().isChat2());
            }
            userRepo.save(optionalUser.get());
        }else {
            log.error("user not found");
        }
    }


    public List<User> findChatUsersList(String chat){
        return userRepo.findChatUsersList(chat);
    }

    public List<User> findNotChatUsersList(String chat){
        return userRepo.findNotChatUsersList(chat);
    }

}
