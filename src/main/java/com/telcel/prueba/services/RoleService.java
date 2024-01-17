package com.telcel.prueba.services;

import com.telcel.prueba.models.Role;
import com.telcel.prueba.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(String id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Optional<Role> getRoleByType(String tipoDeRole) {
        return roleRepository.findByTipoDeRole(tipoDeRole);
    }

    public Role addRole(Role role) {
        validateRoleName(role.getTipoDeRole());
        ensureRoleDoesNotExist(role.getTipoDeRole());
        role.setId(null);
        return roleRepository.save(role);
    }

    public Role updateRole(String id, String nuevoNombre) {
        Role role = findRoleById(id);
        validateRoleName(nuevoNombre);
        ensureUniqueRoleName(id, nuevoNombre);
        role.setTipoDeRole(nuevoNombre);
        return roleRepository.save(role);
    }

    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }

    private void validateRoleName(String roleName) {
        if (roleName == null || roleName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del rol no puede estar vacío.");
        }
    }

    private void ensureRoleDoesNotExist(String roleName) {
        Optional<Role> existingRole = roleRepository.findByTipoDeRole(roleName);
        if (existingRole.isPresent()) {
            throw new IllegalArgumentException("Ya existe un rol con el nombre proporcionado.");
        }
    }

    private Role findRoleById(String id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un rol con el ID proporcionado"));
    }

    private void ensureUniqueRoleName(String id, String nuevoNombre) {
        Optional<Role> existingRole = roleRepository.findByTipoDeRole(nuevoNombre);
        if (existingRole.isPresent() && !existingRole.get().getId().equals(id)) {
            throw new IllegalArgumentException("Ya existe un rol con el nuevo nombre proporcionado.");
        }
    }
}
