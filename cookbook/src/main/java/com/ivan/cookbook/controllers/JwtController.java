package com.ivan.cookbook.controllers;

import com.ivan.cookbook.dtos.AuthRequestDTO;
import com.ivan.cookbook.dtos.AuthResponseDTO;
import com.ivan.cookbook.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO authRequest) {
        // Authenticate the user, check credentials, etc.
        // Assuming you have a method to authenticate the user in your UserRepository

        // Generate JWT token
        String token = jwtService.generateToken(authRequest.getEmail());

        // Create the response DTO
        AuthResponseDTO responseDTO = new AuthResponseDTO(token);

        // Return the token in the response
        return ResponseEntity.ok(responseDTO);
    }
}
