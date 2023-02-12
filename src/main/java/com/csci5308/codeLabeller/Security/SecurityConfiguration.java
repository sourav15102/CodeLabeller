package com.csci5308.codeLabeller.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //first run script from user.ddl
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain authenticationFilter(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/signup")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
//                .loginPage("/login")
//                .permitAll();

        return httpSecurity.build();
    }

//    // In memory storage
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//        User user1 = (User) User.withUsername("sghai")
//                .password(passwordEncoder().encode("1234"))
//                .authorities("ANNOTATOR")
//                .build();
//
//        User user2 = (User) User.withUsername("aghai")
//                .password(passwordEncoder().encode("1234"))
//                .authorities("ADMIN", "ANNOTATOR")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }

}
