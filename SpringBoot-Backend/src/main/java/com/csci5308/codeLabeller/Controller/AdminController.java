package com.csci5308.codeLabeller.Controller;

import com.csci5308.codeLabeller.Models.DTO.AdminSnippetsAnnotationsDTO;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Service.AnnotationService;
import com.csci5308.codeLabeller.Service.SnippetService;
import com.csci5308.codeLabeller.Service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    SurveyService surveyService;
    @Autowired
    AnnotationService annotationService;
    @Autowired
    SnippetService snippetService;

    @Autowired
    UserDetailsManager jdbcUserDetailsManager;

    @PostMapping("{admin_username}/survey/")
    public ResponseEntity<?> saveSurvey(@PathVariable("admin_username") String username, @RequestBody AdminSnippetsAnnotationsDTO asaDTO){Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!jdbcUserDetailsManager.userExists(username)){
            return ResponseEntity.notFound().build();
        }
        asaDTO.setUsername(username);
        CodeSurvey survey = surveyService.createSurvey(asaDTO);
        annotationService.createAnnotations(asaDTO, survey);
        snippetService.createSnippets(asaDTO, survey);
        return ResponseEntity.ok().build();
    }
}
