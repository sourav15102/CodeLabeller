package com.csci5308.codeLabeller.Controller;

import com.csci5308.codeLabeller.Models.DTO.AdminSnippetsAnnotationsDTO;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Models.DTO.AnnotationResponse;
import com.csci5308.codeLabeller.Models.DTO.SnippetResponse;
import com.csci5308.codeLabeller.Models.DTO.SurveyResponse;
import com.csci5308.codeLabeller.Service.AnnotationService;
import com.csci5308.codeLabeller.Service.SnippetService;
import com.csci5308.codeLabeller.Service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@EnableMethodSecurity
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

    @GetMapping("{admin_username}/survey/{survey_id}/annotation/all/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AnnotationResponse> getAllAnnotations(@PathVariable("admin_username") String username,
                                                  @PathVariable("survey_id") Long surveyId){
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        return annotationService.getAllAnnotations(username,surveyId);
    }

    @GetMapping("{admin_username}/survey/{survey_id}/annotation/{id}/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AnnotationResponse getAnnotation(@PathVariable("admin_username") String username,
                                        @PathVariable("survey_id") Long surveyId,
                                        @PathVariable("id") Long id){
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        return annotationService.getAnnotation(username,surveyId,id);
    }

    @GetMapping("{admin_username}/survey/{survey_id}/snippet/all/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<SnippetResponse> getAllSnippets(@PathVariable("admin_username") String username,
                                                @PathVariable("survey_id") Long surveyId){
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        return snippetService.getAllSnippets(username,surveyId);
    }

    @GetMapping("{admin_username}/survey/{survey_id}/snippet/{id}/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SnippetResponse getSnippet(@PathVariable("admin_username") String username,
                                            @PathVariable("survey_id") Long surveyId,
                                            @PathVariable("id") Long id){
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        return snippetService.getSnippet(username,surveyId,id);
    }

    @GetMapping("{admin_username}/survey/all/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<SurveyResponse> getAllSurveys(@PathVariable("admin_username") String username){
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        return surveyService.getAllSurveys(username);
    }

    @GetMapping("{admin_username}/survey/{id}/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SurveyResponse getSurvey(@PathVariable("admin_username") String username, @PathVariable("id") Long id){
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        return surveyService.getSurvey(id);
    }

    @PostMapping("{admin_username}/survey/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> saveSurvey(@PathVariable("admin_username") String username, @RequestBody AdminSnippetsAnnotationsDTO asaDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        asaDTO.setUsername(authentication.getName());
        CodeSurvey survey = surveyService.createSurvey(asaDTO);
        annotationService.createAnnotations(asaDTO, survey);
        snippetService.createSnippets(asaDTO, survey);
        return ResponseEntity.ok().build();
    }


}

