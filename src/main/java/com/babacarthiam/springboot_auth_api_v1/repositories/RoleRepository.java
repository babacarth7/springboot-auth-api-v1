package com.babacarthiam.springboot_auth_api_v1.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.babacarthiam.springboot_auth_api_v1.entities.RoleEntity;
import com.babacarthiam.springboot_auth_api_v1.entities.RoleEnumeration;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(RoleEnumeration name);
}
