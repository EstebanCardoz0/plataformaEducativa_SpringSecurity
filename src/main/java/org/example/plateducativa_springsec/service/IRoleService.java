package org.example.plateducativa_springsec.service;

import org.example.plateducativa_springsec.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    List<Role> findAll();

    Role save(Role role);

    Optional<Role> findById(Long id);

    void deleteById(Long id);

    Role update(Role role);
}

