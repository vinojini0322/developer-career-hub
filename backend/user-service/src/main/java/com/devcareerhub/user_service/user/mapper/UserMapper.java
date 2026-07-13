package com.devcareerhub.user_service.user.mapper;

import com.devcareerhub.user_service.user.dto.CreateUserRequest;
import com.devcareerhub.user_service.user.dto.UpdateUserRequest;
import com.devcareerhub.user_service.user.dto.UserResponse;
import com.devcareerhub.user_service.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(CreateUserRequest request);

    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(
            UpdateUserRequest request,
            @MappingTarget User user
    );
}