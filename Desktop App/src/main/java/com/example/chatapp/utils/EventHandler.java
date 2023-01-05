package com.example.chatapp.utils;

import com.example.chatapp.config.RabbitConfiguration;
import com.example.chatapp.model.entity.MessageBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventHandler {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendMessage(MessageBody messageBody){
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE,RabbitConfiguration.ROUTING_KEY ,messageBody);
    }
}
