package com.devcareerhub.user_service.user.dto;

import com.devcareerhub.user_service.user.enums.UserRole;
import com.devcareerhub.user_service.user.enums.UserStatus;

import java.util.UUID;

public record UserResponse(UUID id,
                           String firstName,
                           String lastName,
                           String email,
                           String phone,
                           UserRole role,
                           UserStatus status) {
}
