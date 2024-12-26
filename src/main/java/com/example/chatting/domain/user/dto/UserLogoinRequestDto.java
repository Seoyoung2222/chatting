package com.example.chatting.domain.user.dto;

import lombok.Data;

@Data
public class UserLogoinRequestDto {
    private String userId;
    private String password;
}
