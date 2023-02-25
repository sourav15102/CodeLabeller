package com.csci5308.codeLabeller.Service;

import com.csci5308.codeLabeller.Enums.UserAuthority;
import com.csci5308.codeLabeller.Models.DTO.AuthResponse;
import com.csci5308.codeLabeller.Models.DTO.UserLoginDetails;
import com.csci5308.codeLabeller.Models.DTO.UserSignUpDetails;
import com.csci5308.codeLabeller.Repsoitory.UserSignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserSignUpService {

    @Autowired
    UserSignUpRepository userSignUpRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authManager;

    public AuthResponse registerUser(UserSignUpDetails user){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAuthority());
        Set<GrantedAuthority> authorities = new HashSet<>();
        if(user.getAuthority().equals(UserAuthority.ADMIN.toString())){
            authorities.add(new SimpleGrantedAuthority(UserAuthority.ANNOTATOR.toString()));
        }
        authorities.add(grantedAuthority);

        userSignUpRepository.registerTheUser(new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), authorities));
        String jwtToken = "JWT Token";
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwtToken(jwtToken);
        return authResponse;
    }

    public AuthResponse authenticate(UserLoginDetails userLoginDetails){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDetails.getUsername(),
                        userLoginDetails.getPassword()
                )
        );
        UserDetails user = userSignUpRepository.findByUsername(userLoginDetails.getUsername());
        String jwtToken = "JWT Token";
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwtToken(jwtToken);
        return authResponse;
    }
}
