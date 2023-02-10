package com.csci5308.codeLabeller.Security;

import jakarta.servlet.FilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.net.http.HttpRequest;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    //first run script from user.ddl
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        System.out.println(new BCryptPasswordEncoder().encode("1234"));
//        return new JdbcUserDetailsManager(dataSource);
//    }

//    // In memory storage
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        User user1 = (User) User.withUsername("sghai")
                .password(passwordEncoder().encode("1234"))
                .authorities("ANNOTATOR")
                .build();

        User user2 = (User) User.withUsername("aghai")
                .password(passwordEncoder().encode("1234"))
                .authorities("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1,user2);
    }

}
