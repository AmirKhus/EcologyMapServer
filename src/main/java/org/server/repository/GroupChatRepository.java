package org.server.repository;

import org.server.model.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupChatRepository extends JpaRepository<GroupChat, Long> {

    boolean existsByGroupChatName(Long groupChatName);
}
