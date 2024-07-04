package com.babacarthiam.springboot_auth_api_v1.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babacarthiam.springboot_auth_api_v1.entities.UserEntity;
import com.babacarthiam.springboot_auth_api_v1.services.UserService;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserEntity> authenticatedEntity() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        UserEntity currentUserEntity = (UserEntity) authentication
                .getPrincipal();

        return ResponseEntity.ok(currentUserEntity);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> allUserEntities() {
        List<UserEntity> userEntities = userService.allUserEntities();

        return ResponseEntity.ok(userEntities);
    }

}
