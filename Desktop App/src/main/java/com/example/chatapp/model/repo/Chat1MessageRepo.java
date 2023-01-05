package com.example.chatapp.model.repo;

import com.example.chatapp.model.entity.Chat1Message;
import com.example.chatapp.model.entity.GeneralMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Chat1MessageRepo extends MongoRepository<Chat1Message, String> {
    Optional<Chat1Message> findFirstByOrderByCreatedTimeDesc();

}
