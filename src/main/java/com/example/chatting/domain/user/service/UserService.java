package com.example.chatting.domain.user.service;

import com.example.chatting.domain.user.dto.UserLoginResponseDto;
import com.example.chatting.domain.user.dto.UserLogoinRequestDto;
import com.example.chatting.domain.user.dto.UserRequestDto;
import com.example.chatting.domain.user.dto.UserResponseDto;
import com.example.chatting.global.api.ApiProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
        String apiUrl = apiProperties.getUrl() + "/users/create";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("app-id", apiProperties.getAppId());
        headers.set("api-key", apiProperties.getApikey());

        HttpEntity<UserRequestDto> request = new HttpEntity<>(userRequestDto, headers);

        ResponseEntity<UserResponseDto> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                request,
                UserResponseDto.class
        );
        return response.getBody();
    }

    public UserLoginResponseDto login(UserLogoinRequestDto logoinRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("app-id", apiProperties.getAppId());
        headers.set("api-key", apiProperties.getApikey());

        HttpEntity<UserLogoinRequestDto> reqeust = new HttpEntity<>(logoinRequestDto, headers);

        try {
            ResponseEntity<UserLoginResponseDto> response = restTemplate.exchange(
                    apiProperties.getUrl()+"/users/login",
                    HttpMethod.POST,
                    reqeust,
                    UserLoginResponseDto.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("로그인 실패"+e.getResponseBodyAsString(),e);
        }
    }

    public UserResponseDto getUser(String userId) {
        String apiUrl = apiProperties.getUrl() + "/users/" + userId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("app-id", apiProperties.getAppId());
        headers.set("api-key", apiProperties.getApikey());

        HttpEntity<Void> reqeust = new HttpEntity<>(headers);

        try {
            ResponseEntity<UserResponseDto> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    reqeust,
                    UserResponseDto.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("사용자 조회 실패: "+ e.getResponseBodyAsString(),e);
        }
    }
}
