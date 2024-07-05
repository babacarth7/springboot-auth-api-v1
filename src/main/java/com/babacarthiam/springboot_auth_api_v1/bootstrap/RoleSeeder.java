package com.babacarthiam.springboot_auth_api_v1.bootstrap;

import java.util.*;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.babacarthiam.springboot_auth_api_v1.entities.RoleEntity;
import com.babacarthiam.springboot_auth_api_v1.entities.RoleEnumeration;
import com.babacarthiam.springboot_auth_api_v1.repositories.RoleRepository;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @SuppressWarnings("null")
    @Override
    public void onApplicationEvent(
            ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    public void loadRoles() {
        RoleEnumeration[] roleNames = new RoleEnumeration[] {
                RoleEnumeration.USER, RoleEnumeration.ADMIN,
                RoleEnumeration.SUPER_ADMIN };

        Map<RoleEnumeration, String> roleDescriptionMap = Map.of(
                RoleEnumeration.USER, "Default user role",
                RoleEnumeration.ADMIN, "Administrator role",
                RoleEnumeration.SUPER_ADMIN, "Super Administrator role");

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<RoleEntity> optionalRole = roleRepository
                    .findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                RoleEntity roleToCreate = new RoleEntity();

                roleToCreate.setName(roleName)
                        .setDescription(roleDescriptionMap.get(roleName));
                roleRepository.save(roleToCreate);
            });
        });
    }

}
