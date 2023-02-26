package com.csci5308.codeLabeller.Controller;

import com.csci5308.codeLabeller.Models.DTO.AuthResponse;
import com.csci5308.codeLabeller.Models.DTO.UserSignUpDetails;
import com.csci5308.codeLabeller.Service.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@EnableMethodSecurity
@RestController
public class SignUpController {

    @Autowired
    UserSignUpService userSignUpService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody UserSignUpDetails user){
        return ResponseEntity.ok(userSignUpService.registerUser(user));
    }

}
