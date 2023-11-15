package com.example.vkr_server.mapper;

import com.example.vkr_server.dto.UserDto;
import com.example.vkr_server.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto toDto(User user);
}
