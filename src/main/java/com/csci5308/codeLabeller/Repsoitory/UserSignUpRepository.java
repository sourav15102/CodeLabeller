package com.csci5308.codeLabeller.Repsoitory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Repository;

@Repository
public class UserSignUpRepository {

    @Autowired
    UserDetailsManager jdbcUserDetailsManager;

    public void registerTheUser(UserDetails user){
        jdbcUserDetailsManager.createUser(user);
    }

    public UserDetails findByUsername(String username){
        return jdbcUserDetailsManager.loadUserByUsername(username);
    }

}
