package com.devcareerhub.user_service.user.controller;

import com.devcareerhub.user_service.user.dto.CreateUserRequest;
import com.devcareerhub.user_service.user.dto.UserResponse;
import com.devcareerhub.user_service.user.mapper.UserMapper;
import com.devcareerhub.user_service.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper.toResponse(userService.createUser(userRequest)));
    }
}
