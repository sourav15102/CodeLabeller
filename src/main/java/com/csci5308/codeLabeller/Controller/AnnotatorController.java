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

    @GetMapping("{annotator_username}/survey/{survey_id}/start/")
    @PreAuthorize("hasAuthority('ANNOTATOR')")
    public Page<StartSurveyResponse> startSurvey(@PathVariable("annotator_username") String username,
                                         @PathVariable("survey_id") Long surveyId,
                                         @RequestParam(defaultValue="0") int page){

        username = SecurityContextHolder.getContext().getAuthentication().getName();
        return startSurveyService.startTheSurvey(surveyId,page);
    }

    @PostMapping("{annotator_username}/survey/{survey_id}/snippet/{snippet_id}/annotationstag/")
    public void tagSnippetWithAnnotations(@PathVariable("annotator_username") String annotatorUsername,
                                                    @PathVariable("survey_id") Long surveyId,
                                                    @PathVariable("snippet_id") Long snippetId,
                                                    @RequestBody List<AnnotationResponse> annotationsTag){
        annotatorUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        annotatorService.tagSnippetWithAnnotations(annotatorUsername, surveyId, snippetId, annotationsTag);
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
