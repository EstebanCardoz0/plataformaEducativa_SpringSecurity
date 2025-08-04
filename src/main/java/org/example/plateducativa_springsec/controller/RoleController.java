package org.example.plateducativa_springsec.controller;

import org.example.plateducativa_springsec.model.Permission;
import org.example.plateducativa_springsec.model.Role;
import org.example.plateducativa_springsec.service.IPermissionService;
import org.example.plateducativa_springsec.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@PreAuthorize("denyAll()")
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Role> getRolesById(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') and hasPermission('CREATE')")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {

        Set<Permission> permi = new HashSet<>();
        Permission readPermission;

        for (Permission per : role.getPermissionsList()) {
            readPermission = permissionService.findById(per.getId()).orElseGet(null);
            if (readPermission != null) {
                permi.add(per);
            }
        }
        role.setPermissionsList(permi);
        Role newRole = roleService.save(role);
        return ResponseEntity.ok(newRole);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/update/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {

        Role rol = roleService.findById(id).orElse(null);
        if (rol != null) {
            rol = role;
        }
        roleService.update(rol);
        return ResponseEntity.ok(rol);
    }


}
