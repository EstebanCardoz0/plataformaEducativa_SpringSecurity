package org.example.plateducativa_springsec.service;

import org.example.plateducativa_springsec.model.UserSec;
import org.example.plateducativa_springsec.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {


    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserSec> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserSec> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserSec Save(UserSec userSec) {
        return userRepository.save(userSec);
    }

    @Override
    public void DeleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserSec update(UserSec userSec) {
        return userRepository.save(userSec);
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}

