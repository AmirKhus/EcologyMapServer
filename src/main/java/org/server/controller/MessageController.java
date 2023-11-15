package org.server.controller;

import org.server.dto.MessageDto;
import org.server.mapper.MessageMapper;
import org.server.model.Message;
import org.server.repository.GroupChatRepository;
import org.server.service.MessageService;
import org.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupChatRepository groupChatRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<MessageDto> allMessages(Authentication authentication) {
        List<Message> messages = this.messageService.getAll();
        return new ResponseEntity(messages.stream().map(m -> MessageMapper.INSTANCE.toDto(m)), HttpStatus.OK);
    }

    @GetMapping("chat/{id}")
    public ResponseEntity<MessageDto> getChat(@PathVariable("id") Long id, Principal principal) {
        boolean groupChatDto = this.groupChatRepository.existsByGroupChatName(id);
        List<Message> messages;
        System.out.println("groupChatDto"+groupChatDto);
        if (!groupChatDto) {
            var sender = userService.findByEmail(principal.getName());
            var reciever = userService.getById(id);
            messages = this.messageService.getChat(sender.get(), reciever);
            return new  ResponseEntity(messages.stream().map(m -> MessageMapper.INSTANCE.toDto(m)), HttpStatus.OK);
        }else{
            messages = this.messageService.findByGroupMessage(id);
            return new  ResponseEntity(messages.stream().map(m -> MessageMapper.INSTANCE.toDto(m)), HttpStatus.OK);
        }
    }

    @GetMapping("chatGroup")
    public ResponseEntity<MessageDto> getChatGroup(@PathVariable("id") Long id) {
//        GroupChat groupChatDto = this.groupChatRepository.findGroupChatByGroupChatName(new User(3L,"RT","RT","$2a$10$MWZGRx0Hc0gW5UtQ53eYA.MHGBTKBLVtX87PsJ392bo5kSH52iaUy"));
//        System.out.println(groupChatDto.getGroupChat().getUsername());
        List<Message> messages = this.messageService.findByGroupMessage(id);
        return new  ResponseEntity(messages.stream().map(m -> MessageMapper.INSTANCE.toDto(m)), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<MessageDto> getMessage(@PathVariable("id") Long id) {
        Message message = messageService.getById(id);
        return new ResponseEntity<>(MessageMapper.INSTANCE.toDto(message), HttpStatus.OK);
    }

}
