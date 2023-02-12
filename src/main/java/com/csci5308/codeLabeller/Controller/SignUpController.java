package com.csci5308.codeLabeller.Controller;

import com.csci5308.codeLabeller.Models.UserSignUpDetails;
import com.csci5308.codeLabeller.Service.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@EnableMethodSecurity
@RestController
public class SignUpController {

    @Autowired
    UserSignUpService userSignUpService;
    @PostMapping("/signup")
    public void signUp(@RequestBody UserSignUpDetails user){
        userSignUpService.registerUser(user);
    }

}
