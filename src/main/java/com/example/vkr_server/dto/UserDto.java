package com.example.vkr_server.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
    Long id;
    String email;
    String username;

    public UserDto() {
    }

    public UserDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public UserDto(Long id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
