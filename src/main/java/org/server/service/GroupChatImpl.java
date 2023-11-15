package org.server.service;

import org.server.repository.GroupChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupChatImpl implements GroupChatService {
    @Autowired
    GroupChatRepository groupChatRepository;


    @Override
    public boolean getByIdNameChat(Long id) {
        return groupChatRepository.existsByGroupChatName(id);
    }
}
