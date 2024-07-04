package com.babacarthiam.springboot_auth_api_v1.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.babacarthiam.springboot_auth_api_v1.entities.UserEntity;
import com.babacarthiam.springboot_auth_api_v1.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> allUserEntities() {
        List<UserEntity> userEntities = new ArrayList<>();

        userRepository.findAll().forEach(userEntities::add);

        return userEntities;
    }
}
