package com.example.chatapp.model.repo;

import com.example.chatapp.model.entity.GeneralMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralMessageRepo extends MongoRepository<GeneralMessage, String> {

}
