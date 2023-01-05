package com.example.chatapp.utils;

import com.example.chatapp.views.Chat2;
import com.example.chatapp.views.Chat3;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Data
public class Intermediate {
    @Autowired
    private Chat2 chat2;

    @Autowired
    private Chat3 chat3;

    public void reloadChats(String chatName){
        if (chatName.equals("chat1")){
            chat2.reload();
        }else{
            chat3.reload();
        }


    }
}
