package com.csci5308.codeLabeller.Controller;

import com.csci5308.codeLabeller.Models.CodeSnippet;
import com.csci5308.codeLabeller.Models.DTO.AnnotationResponse;
import com.csci5308.codeLabeller.Models.DTO.StartSurveyResponse;
import com.csci5308.codeLabeller.Models.DTO.SurveyResponse;
import com.csci5308.codeLabeller.Service.AnnotatorService;
import com.csci5308.codeLabeller.Service.StartSurveyService;
import com.csci5308.codeLabeller.Service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.List;

@CrossOrigin
@RestController
@EnableMethodSecurity
@RequestMapping("/annotator/")
public class AnnotatorController {

    @Autowired
    AnnotatorService annotatorService;

    @Autowired
    StartSurveyService startSurveyService;

    @GetMapping("{annotator_username}/survey/all/")
    @PreAuthorize("hasAuthority('ANNOTATOR')")
    public List<SurveyResponse> getAllSurveys(@PathVariable("annotator_username") String username) {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        return annotatorService.getAllSurveys();
    }

//    @GetMapping("{annotator_username}/survey/{survey_id}/start/")
//    @PreAuthorize("hasAuthority('ANNOTATOR')")
//    public StartSurveyResponse startSurvey(@PathVariable("annotator_username") String username,
//                                           @PathVariable("survey_id") Long surveyId){
//
//        username = SecurityContextHolder.getContext().getAuthentication().getName();
//        return startSurveyService.startTheSurvey(surveyId);
//    }

    @PostMapping("{annotator_username}/survey/{survey_id}/start/")
    @PreAuthorize("hasAuthority('ANNOTATOR')")
    public Page<StartSurveyResponse> startSurvey(@PathVariable("annotator_username") String username,
                                         @PathVariable("survey_id") Long surveyId,
                                         @RequestParam(value = "page", defaultValue="0") int page,
                                         @RequestParam(value = "snippetId", defaultValue = "0") long snippetId,
                                         @RequestBody(required = false) List<AnnotationResponse> annotationTags){

        username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(annotationTags!=null){
            annotatorService.tagSnippetWithAnnotations(username,surveyId,snippetId,annotationTags);
        }
        return startSurveyService.startTheSurvey(surveyId,page);
    }

    @GetMapping("{annotator_username}/survey/approved/all/")
    @PreAuthorize("hasAuthority('ANNOTATOR')")
    public List<SurveyResponse> getAllApprovedSurveys(@PathVariable("annotator_username") String username){
        username  = SecurityContextHolder.getContext().getAuthentication().getName();
        return annotatorService.getAllApprovedSurveys(username);
    }

    @GetMapping("{annotator_username}/survey/pending/all/")
    @PreAuthorize("hasAuthority('ANNOTATOR')")
    public List<SurveyResponse> getAllPendingSurveys(@PathVariable("annotator_username") String username){
        username  = SecurityContextHolder.getContext().getAuthentication().getName();
        return annotatorService.getAllPendingSurveys(username);
    }

}
