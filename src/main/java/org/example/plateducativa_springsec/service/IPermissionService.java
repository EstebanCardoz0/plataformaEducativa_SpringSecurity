package org.example.plateducativa_springsec.service;

import org.example.plateducativa_springsec.model.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    List<Permission> findAll();

    Optional<Permission> findById(Long id);

    Permission save(Permission permission);

    void deleteById(Long id);

    Permission update(Permission permission);

}

