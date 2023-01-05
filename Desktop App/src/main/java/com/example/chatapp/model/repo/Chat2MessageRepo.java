package com.example.chatapp.model.repo;

import com.example.chatapp.model.entity.Chat1Message;
import com.example.chatapp.model.entity.Chat2Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Chat2MessageRepo extends MongoRepository<Chat2Message, String> {

}
