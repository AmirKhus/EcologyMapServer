package com.example.vkr_server.repository;

import com.example.vkr_server.model.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupChatRepository extends JpaRepository<GroupChat, Long> {

    boolean existsByGroupChatName(Long groupChatName);
}
