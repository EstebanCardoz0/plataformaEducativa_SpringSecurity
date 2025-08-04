package org.example.plateducativa_springsec.controller;

import org.example.plateducativa_springsec.model.Role;
import org.example.plateducativa_springsec.model.UserSec;
import org.example.plateducativa_springsec.service.IRoleService;
import org.example.plateducativa_springsec.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("denyAll()")
public class UserController {

    @Autowired
    private IRoleService RoleService;
    @Autowired
    private IUserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<UserSec>> getAllUsers() {

        List<UserSec> userList = userService.findAll();

        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<UserSec> getUserById(@PathVariable Long id) {

        Optional<UserSec> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN') and hasPermission('CREATE')")
    @PostMapping
    public ResponseEntity<UserSec> createUser(@RequestBody UserSec user) {
        Set<Role> roleList = new HashSet<>();
        Role readRole;

        user.setPassword(userService.encriptPassword(user.getPassword()));

        for (Role role : user.getRolesList()) {
            readRole = RoleService.findById(role.getId()).orElse(null);
            if (readRole != null) {
                roleList.add(readRole);
            }
        }
        if (!roleList.isEmpty()) {
            user.setRolesList(roleList);
            UserSec newUser = userService.Save(user);
            return ResponseEntity.ok(newUser);
        }
        return null;
    }
}
