package com.telcel.prueba.repositories;

import com.telcel.prueba.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByTipoDeRole(String tipoDeRole);
}
