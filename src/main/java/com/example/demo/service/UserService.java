package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;



    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }



    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }




    public User addUser(User user) {
        // Verificar que el usuario no sea nulo
        if (user == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }

        // Verificar que el rol del usuario exista
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            Role existingRole = roleService.getRoleById(role.getId());
            if (existingRole == null) {
                throw new IllegalArgumentException("El rol con ID " + role.getId() + " no existe.");
            }
        }

        // Puedes realizar otras validaciones del usuario si es necesario

        // Guardar el usuario en la base de datos
        return userRepository.save(user);
    }





    public User updateUser(String id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    // Puedes agregar lógica adicional antes de actualizar

                    // Actualizar los campos del usuario
                    user.setNombre(updatedUser.getNombre());
                    user.setApellidoPaterno(updatedUser.getApellidoPaterno());
                    user.setApellidoMaterno(updatedUser.getApellidoMaterno());

                    // Actualizar la referencia al rol
                    user.setRoles(updatedUser.getRoles());

                    // Puedes seguir actualizando otros campos según tus necesidades

                    // Guardar el usuario actualizado en la base de datos
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un usuario con el ID proporcionado"));
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


}
