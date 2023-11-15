package com.example.vkr_server.mapper;

import com.example.vkr_server.dto.MessageDto;
import com.example.vkr_server.dto.MessageGroupDto;
import com.example.vkr_server.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserMapper.class})
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(source="sender", target="sender")
    @Mapping(source="target", target="target")
    MessageDto toDto(Message message);
    @Mapping(source="id", target="id")
    MessageDto toDto(MessageGroupDto m);
}
