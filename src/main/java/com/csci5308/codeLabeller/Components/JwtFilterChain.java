package com.csci5308.codeLabeller.Components;

import com.csci5308.codeLabeller.Enums.JwtEnum;
import com.csci5308.codeLabeller.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilterChain extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserDetailsManager userDetailsManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(JwtEnum.AUTHORIZATION.toString());
        String username,jwtToken;
        UserDetails userDetails = null;
        WebAuthenticationDetails webAuthenticationDetails = null;

        if(authHeader!=null && authHeader.startsWith(JwtEnum.Bearer.toString()+" ")){
            jwtToken = authHeader.substring(7);
            username = jwtService.getUsername(jwtToken);
            userDetails = userDetailsManager.loadUserByUsername(username);
            if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
                if(jwtService.isValid(jwtToken, userDetails)){
                    UsernamePasswordAuthenticationToken unpa = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                    webAuthenticationDetails =  new WebAuthenticationDetailsSource().buildDetails(request);
                    unpa.setDetails(webAuthenticationDetails);
                    SecurityContextHolder.getContext().setAuthentication(unpa);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
