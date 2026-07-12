package com.devcareerhub.user_service.user.service.impl;

import com.devcareerhub.user_service.user.dto.CreateUserRequest;
import com.devcareerhub.user_service.user.entity.User;
import com.devcareerhub.user_service.user.mapper.UserMapper;
import com.devcareerhub.user_service.user.repository.UserRepository;
import com.devcareerhub.user_service.user.service.UserService;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    @Transactional
    public User createUser(CreateUserRequest userRequest) {
        if(Boolean.TRUE.equals(userRepository.existsByEmail(userRequest.email()))){
            throw new DuplicateRequestException("Email already exists");
        }
        return userRepository.save(UserMapper.toEntity(userRequest));
    }
}
