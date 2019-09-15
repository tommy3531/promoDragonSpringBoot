package com.tommarler.growthDragon.repository;

import com.tommarler.growthDragon.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}
