package com.csci5308.codeLabeller.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@EnableMethodSecurity
@RestController
public class MainController {

    @GetMapping("/annotator")
    @PreAuthorize("hasAuthority('ANNOTATOR')")
    public String getLabeller(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName()+" is Annotator";
    }
//    @GetMapping("/admin")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    public String getAdmin() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication.getName() + " is admin";
//    }
}
