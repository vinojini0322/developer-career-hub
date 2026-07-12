package com.devcareerhub.user_service.user.mapper;

import com.devcareerhub.user_service.user.dto.CreateUserRequest;
import com.devcareerhub.user_service.user.dto.UserResponse;
import com.devcareerhub.user_service.user.entity.User;
import com.devcareerhub.user_service.user.enums.UserRole;
import com.devcareerhub.user_service.user.enums.UserStatus;


public class UserMapper {
    public UserMapper() {
    }

    public static User toEntity(CreateUserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .email(userRequest.email())
                .phone(userRequest.phone())
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE).build();
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(),
                user.getRole(), user.getStatus());

    }
}
