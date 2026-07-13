package com.devcareerhub.user_service.user.service.impl;

import com.devcareerhub.user_service.user.dto.CreateUserRequest;
import com.devcareerhub.user_service.user.entity.User;
import com.devcareerhub.user_service.user.mapper.UserMapper;
import com.devcareerhub.user_service.user.repository.UserRepository;
import com.devcareerhub.user_service.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public User createUser(CreateUserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.email())) {
            throw new RuntimeException(
                    "A user already exists with email: " + userRequest.email()
            );
        }

        User user = userMapper.toEntity(userRequest);

        return userRepository.save(user);
    }
}