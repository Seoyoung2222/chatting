package com.example.chatting.domain.user.dto;

import lombok.Data;

import java.util.Map;

@Data
public class UserResponseDto {
    private User user;
    private String loginToken;

    @Data
    public static class User {
        private String id;
        private String username;
        private String profileImageUrl;
        private boolean disablePushNotification;
        private Map<String, String> data;
        private Long updateAt;
        private Long createAt;
    }
}
