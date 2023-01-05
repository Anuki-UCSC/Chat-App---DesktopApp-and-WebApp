package com.example.chatapp.model.repo;

import com.example.chatapp.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User,String> {
    Optional<User> findByUsername(String username);

    @Query("{ ?0: true }")
    List<User> findChatUsersList(String chat);

    @Query("{ ?0: false }")
    List<User> findNotChatUsersList(String chat);
}
