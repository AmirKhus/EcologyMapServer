package org.server.mapper;

import org.server.dto.MessageDto;
import org.server.dto.MessageGroupDto;
import org.server.model.Message;
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
