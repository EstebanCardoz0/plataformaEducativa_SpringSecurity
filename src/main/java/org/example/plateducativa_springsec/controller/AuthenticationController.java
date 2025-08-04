package org.example.plateducativa_springsec.controller;

import jakarta.validation.Valid;
import org.example.plateducativa_springsec.dto.AuthLoginRequestDTO;
import org.example.plateducativa_springsec.dto.AuthResponseDTO;
import org.example.plateducativa_springsec.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
        System.out.println("Email recibido: " + userRequest.username());
        System.out.println("Password recibido: " + userRequest.password());
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

}
