package com.example.chatting.domain.user.service;

import com.example.chatting.domain.user.dto.UserRequestDto;
import com.example.chatting.domain.user.dto.UserResponseDto;
import com.example.chatting.global.api.ApiProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;

    public UserService(RestTemplate restTemplate, ApiProperties apiProperties) {
        this.restTemplate = restTemplate;
        this.apiProperties = apiProperties;
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("app-id", apiProperties.getAppId());
        headers.set("api-key", apiProperties.getApikey());

        HttpEntity<UserRequestDto> request = new HttpEntity<>(userRequestDto, headers);

        ResponseEntity<UserResponseDto> response = restTemplate.exchange(
                apiProperties.getUrl(),
                HttpMethod.POST,
                request,
                UserResponseDto.class
        );
        return response.getBody();
    }

}
