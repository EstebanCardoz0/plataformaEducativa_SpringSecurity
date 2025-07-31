package org.example.plateducativa_springsec.service;

import org.example.plateducativa_springsec.model.UserSec;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserSec> findAll();

    Optional<UserSec> findById(Long id);

    UserSec Save(UserSec userSec);

    void DeleteById(Long id);

    UserSec update(UserSec userSec);

    String encriptPassword(String password);

}

