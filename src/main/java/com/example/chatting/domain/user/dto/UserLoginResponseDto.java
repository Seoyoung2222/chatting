package com.example.chatting.domain.user.dto;

import lombok.Data;

@Data
public class UserLoginResponseDto {
    private String loginToken;
    private User user;

    @Data
    public static class User {
        private String id;
        private String username;
        private String profileImageUrl;
        private boolean disablePushNotification;
    }
}
