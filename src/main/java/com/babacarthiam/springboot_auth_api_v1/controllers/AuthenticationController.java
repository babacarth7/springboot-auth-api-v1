package com.babacarthiam.springboot_auth_api_v1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babacarthiam.springboot_auth_api_v1.dtos.LoginUserDto;
import com.babacarthiam.springboot_auth_api_v1.dtos.RegisterUserDto;
import com.babacarthiam.springboot_auth_api_v1.entities.UserEntity;
import com.babacarthiam.springboot_auth_api_v1.responses.LoginResponse;
import com.babacarthiam.springboot_auth_api_v1.services.AuthenticationService;
import com.babacarthiam.springboot_auth_api_v1.services.JwtService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    public final JwtService jwtService;
    public final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService,
            AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(
            @RequestBody RegisterUserDto registerUserDto) {
        UserEntity registeredUserEntity = authenticationService
                .register(registerUserDto);
        return ResponseEntity.ok(registeredUserEntity);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginUserDto loginUserDto) {
        UserEntity loginUserEntity = authenticationService.login(loginUserDto);

        String jwtToken = jwtService.generateToken(loginUserEntity);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken)
                .setExpiresIn(jwtService.getJwtExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
