package com.csci5308.codeLabeller.Controller;

import com.csci5308.codeLabeller.Models.DTO.AdminSnippetsAnnotationsDTO;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Service.AnnotationService;
import com.csci5308.codeLabeller.Service.SnippetService;
import com.csci5308.codeLabeller.Service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping("{admin_username}/survey/")
    public void saveSurvey(@PathVariable("admin_username") String username, @RequestBody AdminSnippetsAnnotationsDTO asaDTO){Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        asaDTO.setUsername(username);
        CodeSurvey survey = surveyService.createSurvey(asaDTO);
        annotationService.createAnnotations(asaDTO, survey);
        snippetService.createSnippets(asaDTO, survey);
    }
}
