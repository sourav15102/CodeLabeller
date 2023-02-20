package com.csci5308.codeLabeller.Controller;

import com.csci5308.codeLabeller.Models.AdminSnippetsAnnotationsDTO;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Service.AnnotationService;
import com.csci5308.codeLabeller.Service.SnippetService;
import com.csci5308.codeLabeller.Service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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

    @PostMapping("{admin_id}/survey/")
    public void saveSurvey(@PathVariable("admin_id") Long id, @RequestBody AdminSnippetsAnnotationsDTO asaDTO){
        asaDTO.setAdminID(id);
        CodeSurvey survey = surveyService.createSurvey(asaDTO);
        annotationService.createAnnotations(asaDTO, survey);
        snippetService.createSnippets(asaDTO, survey);
    }
}
