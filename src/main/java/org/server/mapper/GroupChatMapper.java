package org.server.mapper;

import org.server.dto.GroupChatDto;
import org.server.model.GroupChat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupChatMapper {
    GroupChat INSTANCE = Mappers.getMapper(GroupChat.class);

    GroupChatDto toDto(GroupChat groupChat);
}
