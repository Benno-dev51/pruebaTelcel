package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Role getRoleById(@PathVariable String id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Role updateRole(@PathVariable String id, @RequestBody Map<String, String> roleUpdate) {
        String nuevoNombre = roleUpdate.get("tipoDeRole");
        return roleService.updateRole(id, nuevoNombre);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable String id) {
        roleService.deleteRole(id);
    }
}
