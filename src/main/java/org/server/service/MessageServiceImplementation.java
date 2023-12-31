package org.server.service;

import org.server.model.Message;
import org.server.model.User;
import org.server.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService{
    @Autowired
    MessageRepository messageRepository;

    @Override
    public Message getById(Long id) {
        return messageRepository.getById(id);
    }

    @Override
    public void save(Message message) {messageRepository.save(message);}

    @Override
    public void delete(long id) {messageRepository.deleteById(id);}

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getChat(User sender, User target) {
        return messageRepository.findAllBySenderAndTargetOrTargetAndSenderOrderByCreatedAtAsc(sender, target, sender, target);
    }

    @Override
    public List<Message> findByGroupMessage(Long id) {
        return messageRepository.findByGroupMessage(id);
    }

}
