package com.csci5308.codeLabeller.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMethodSecurity
public class MainController {

    @GetMapping("/annotator")
    @PreAuthorize("hasAuthority('ANNOTATOR')")
    public String getLabeller(){
        return "I am Annotator";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String getAdmin(){
        return "I am admin";
    }
}
