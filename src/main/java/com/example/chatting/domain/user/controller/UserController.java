package com.example.chatting.domain.user.controller;

import com.example.chatting.domain.user.dto.UserLoginResponseDto;
import com.example.chatting.domain.user.dto.UserLogoinRequestDto;
import com.example.chatting.domain.user.dto.UserRequestDto;
import com.example.chatting.domain.user.dto.UserResponseDto;
import com.example.chatting.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.createUser(userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLogoinRequestDto loginRequestDto) {
        UserLoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        return ResponseEntity.ok(loginResponseDto);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }
}