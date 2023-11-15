package com.example.vkr_server.dto;

import java.io.Serializable;

public class MessageDto implements Serializable {
    String id;
    String text;
    UserDto sender;
    UserDto target;
    String createdAt;

    public MessageDto() {
    }

    public MessageDto(String id, String text, UserDto sender, UserDto target, String createdAt) {
        this.id = id;
        this.text = text;
        this.sender = sender;
        this.target = target;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserDto getSender() {
        return sender;
    }

    public void setSender(UserDto sender) {
        this.sender = sender;
    }

    public UserDto getTarget() {
        return target;
    }

    public void setTarget(UserDto target) {
        this.target = target;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
