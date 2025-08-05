package org.example.plateducativa_springsec.service;

import jakarta.transaction.Transactional;
import org.example.plateducativa_springsec.model.Permission;
import org.example.plateducativa_springsec.model.Role;
import org.example.plateducativa_springsec.repository.IPermissionRepository;
import org.example.plateducativa_springsec.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public Role save(Role role) {

        if (role.getPermissionsList() != null && !role.getPermissionsList().isEmpty()) {

            Set<Permission> managedPermissions = new HashSet<>();

            for (Permission permi : role.getPermissionsList()) {
                Permission managedPermi = permissionRepository.findById(permi.getId()).orElseThrow(() -> new RuntimeException("Permiso no encontrado: " + permi.getId()));
                managedPermissions.add(managedPermi);
            }
            role.setPermissionsList(managedPermissions);
        }


        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }
}

