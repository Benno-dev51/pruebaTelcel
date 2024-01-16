package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
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

    public Role addRole(Role role) {
        // Validar que el nombre del rol no sea nulo o vacío
        if (role.getTipoDeRole() == null || role.getTipoDeRole().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del rol no puede estar vacío.");
        }


        Optional<Role> existingRole = roleRepository.findByTipoDeRole(role.getTipoDeRole());
        if (existingRole.isPresent()) {
            throw new IllegalArgumentException("Ya existe un rol con el nombre proporcionado.");
        }


        role.setId(null);

        return roleRepository.save(role);
    }




    public Role updateRole(String id, String nuevoNombre) {
        // Buscar el rol por ID
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un rol con el ID proporcionado"));

        // Validar que el nuevo nombre no sea nulo o vacío
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nuevo nombre del rol no puede estar vacío.");
        }

        // Verificar si ya existe un rol con el nuevo nombre
        Optional<Role> existingRole = roleRepository.findByTipoDeRole(nuevoNombre);
        if (existingRole.isPresent() && !existingRole.get().getId().equals(id)) {
            throw new IllegalArgumentException("Ya existe un rol con el nuevo nombre proporcionado.");
        }

        // Actualizar el nombre del rol
        role.setTipoDeRole(nuevoNombre);

        // Guardar el rol actualizado en la base de datos
        return roleRepository.save(role);
    }










    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }
}
