package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User addUser(User user) {
        validateUser(user);
        setRoleIds(user.getRoles());
        return userRepository.save(user);
    }

    public User updateUser(String id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    if (StringUtils.isBlank(updatedUser.getNombre()) || StringUtils.isBlank(updatedUser.getApellidoPaterno())) {
                        throw new IllegalArgumentException("Nombre y apellido paterno son campos obligatorios.");
                    }

                    if (!id.equals(updatedUser.getId())) {
                        throw new IllegalArgumentException("El ID proporcionado no coincide con el ID del usuario a actualizar.");
                    }

                    validateRolesExist(updatedUser.getRoles());
                    updateFields(user, updatedUser);

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un usuario con el ID proporcionado"));
    }


    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }
        validateRolesExist(user.getRoles());
    }

    private void validateRolesExist(List<Role> roles) {
        for (Role role : roles) {
            Optional<Role> existingRole = roleService.getRoleByType(role.getTipoDeRole());
            if (existingRole.isEmpty()) {
                throw new IllegalArgumentException("El rol con tipo " + role.getTipoDeRole() + " no existe.");
            }
            role.setId(existingRole.get().getId());
        }
    }

    private void setRoleIds(List<Role> roles) {
        for (Role role : roles) {
            Optional<Role> existingRole = roleService.getRoleByType(role.getTipoDeRole());
            role.setId(existingRole.get().getId());
        }
    }

    private void updateFields(User user, User updatedUser) {
        user.setNombre(updatedUser.getNombre());
        user.setApellidoPaterno(updatedUser.getApellidoPaterno());
        user.setApellidoMaterno(updatedUser.getApellidoMaterno());
        updateRoles(user, updatedUser.getRoles());
    }

    private void updateRoles(User user, List<Role> updatedRoles) {
        validateRolesExist(updatedRoles);
        setRoleIds(updatedRoles);
        user.setRoles(updatedRoles);
    }
}
