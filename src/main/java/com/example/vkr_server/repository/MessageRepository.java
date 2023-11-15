package com.example.vkr_server.repository;

import com.example.vkr_server.model.Message;
import com.example.vkr_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {


    List<Message> findAllBySenderAndTargetOrTargetAndSenderOrderByCreatedAtAsc(User user, User target, User target_, User sender_);

    @Query(value = "SELECT * from messages WHERE target=:id", nativeQuery = true)
    List<Message> findByGroupMessage(Long id);
}
