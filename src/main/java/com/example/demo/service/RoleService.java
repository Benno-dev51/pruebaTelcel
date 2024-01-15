package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        // Puedes agregar lógica adicional antes de guardar
        return roleRepository.save(role);
    }

    public Role updateRole(String id, Role role) {
        role.setTipoDeRole(id);
        // Puedes agregar lógica adicional antes de guardar
        return roleRepository.save(role);
    }

    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }
}
