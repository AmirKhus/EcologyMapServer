package com.example.vkr_server.mapper;

import com.example.vkr_server.dto.GroupChatDto;
import com.example.vkr_server.model.GroupChat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupChatMapper {
    GroupChat INSTANCE = Mappers.getMapper(GroupChat.class);

    GroupChatDto toDto(GroupChat groupChat);
}
