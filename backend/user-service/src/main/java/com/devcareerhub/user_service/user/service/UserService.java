package com.devcareerhub.user_service.user.service;

import com.devcareerhub.user_service.user.dto.CreateUserRequest;
import com.devcareerhub.user_service.user.entity.User;

public interface UserService {

    User createUser(CreateUserRequest userRequest);
}
