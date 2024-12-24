package com.example.chatting.domain.user.dto;

import lombok.Data;

import java.util.Map;

@Data
public class UserRequestDto {
    private String userId;
    private String password;
    private String username;
    private String profileImageUrl;
    private Map<String, String> data;
}
