package com.babacarthiam.springboot_auth_api_v1.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.babacarthiam.springboot_auth_api_v1.dtos.LoginUserDto;
import com.babacarthiam.springboot_auth_api_v1.dtos.RegisterUserDto;
import com.babacarthiam.springboot_auth_api_v1.entities.UserEntity;
import com.babacarthiam.springboot_auth_api_v1.repositories.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public UserEntity register(RegisterUserDto input) {
        UserEntity user = new UserEntity().setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public UserEntity login(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(),
                        input.getPassword()));
        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}
