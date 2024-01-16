package com.example.demo.repository;

import com.example.demo.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role,String> {
    Optional<Role> findByTipoDeRole(String tipoDeRole);
}
