package org.server.service;

import org.server.model.Message;
import org.server.model.User;

import java.util.List;

public interface MessageService {
    Message getById(Long id);

    void save(Message user);

    void delete(long id);

    List<Message> getAll();

    List<Message> getChat(User sender, User target);

    List<Message> findByGroupMessage(Long id);

}
