package com.babacarthiam.springboot_auth_api_v1.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.babacarthiam.springboot_auth_api_v1.dtos.RegisterUserDto;
import com.babacarthiam.springboot_auth_api_v1.entities.RoleEntity;
import com.babacarthiam.springboot_auth_api_v1.entities.RoleEnumeration;
import com.babacarthiam.springboot_auth_api_v1.entities.UserEntity;
import com.babacarthiam.springboot_auth_api_v1.repositories.RoleRepository;
import com.babacarthiam.springboot_auth_api_v1.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserEntity> allUserEntities() {
        List<UserEntity> userEntities = new ArrayList<>();

        userRepository.findAll().forEach(userEntities::add);

        return userEntities;
    }

    public UserEntity createAdministrator(RegisterUserDto input) {
       Optional<RoleEntity> optionalRole = roleRepository.findByName(RoleEnumeration.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new UserEntity().setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRoleEntity(optionalRole.get());
        
        return userRepository.save(user);
    }
}
