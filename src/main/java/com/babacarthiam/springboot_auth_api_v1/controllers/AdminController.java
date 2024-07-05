package com.babacarthiam.springboot_auth_api_v1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babacarthiam.springboot_auth_api_v1.dtos.RegisterUserDto;
import com.babacarthiam.springboot_auth_api_v1.entities.UserEntity;
import com.babacarthiam.springboot_auth_api_v1.services.UserService;

@RequestMapping("/admins")
@RestController
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<UserEntity> createAdministrator(@RequestBody RegisterUserDto registerUserDto) {
        UserEntity createAdmin = userService.createAdministrator(registerUserDto);

        return ResponseEntity.ok(createAdmin);
    }
}
