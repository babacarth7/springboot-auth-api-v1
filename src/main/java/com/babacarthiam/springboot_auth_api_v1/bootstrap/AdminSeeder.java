package com.babacarthiam.springboot_auth_api_v1.bootstrap;

import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.babacarthiam.springboot_auth_api_v1.dtos.RegisterUserDto;
import com.babacarthiam.springboot_auth_api_v1.entities.RoleEntity;
import com.babacarthiam.springboot_auth_api_v1.entities.RoleEnumeration;
import com.babacarthiam.springboot_auth_api_v1.entities.UserEntity;
import com.babacarthiam.springboot_auth_api_v1.repositories.RoleRepository;
import com.babacarthiam.springboot_auth_api_v1.repositories.UserRepository;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(RoleRepository roleRepository,
            UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(
            @SuppressWarnings("null") ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperAdministrator();
    }

    public void createSuperAdministrator() {
        RegisterUserDto userDto = new RegisterUserDto();
        userDto.setFullName("Super Admin").setEmail("super.admin@email.com")
                .setPassword("abc123");

        Optional<RoleEntity> optionalRole = roleRepository
                .findByName(RoleEnumeration.SUPER_ADMIN);
        Optional<UserEntity> optionalUser = userRepository
                .findByEmail(userDto.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new UserEntity().setFullName(userDto.getFullName())
                .setEmail(userDto.getEmail())
                .setPassword(passwordEncoder.encode(userDto.getPassword()))
                .setRoleEntity(optionalRole.get());

        userRepository.save(user);
    }
}
