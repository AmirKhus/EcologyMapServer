package org.server.controller;

import org.server.dto.MessageCreateDto;
import org.server.mapper.MessageMapper;
import org.server.model.Message;
import org.server.service.MessageService;
import org.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;


@RestController
public class ChatController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(@RequestBody @Valid MessageCreateDto message, Principal principal) {
        Message newMessage = new Message();
        newMessage.setSender(userService.findByEmail(principal.getName()).get());
        newMessage.setTarget(userService.getById(message.getTarget()));
        newMessage.setText(message.getText());
        messageService.save(newMessage);

        var messageDto = MessageMapper.INSTANCE.toDto(newMessage);
//        return new ResponseEntity<>(messageDto, HttpStatus.OK);
        messagingTemplate.convertAndSendToUser(
                message.getTarget().toString(), "/queue/messages",
                messageDto
        );
    }
}
