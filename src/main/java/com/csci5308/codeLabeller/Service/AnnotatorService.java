package com.csci5308.codeLabeller.Service;

import com.csci5308.codeLabeller.Models.Annotator;
import com.csci5308.codeLabeller.Models.CodeAnnotations;
import com.csci5308.codeLabeller.Models.CodeSnippet;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Models.DTO.AnnotationResponse;
import com.csci5308.codeLabeller.Models.DTO.SurveyResponse;
import com.csci5308.codeLabeller.Repsoitory.AnnotatorRepository;
import com.csci5308.codeLabeller.Repsoitory.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AnnotatorService {

    @Autowired
    AnnotatorRepository annotatorRepository;
    @Autowired
    SurveyService surveyService;
    @Autowired
    AnnotationService annotationService;
    @Autowired
    SnippetService snippetService;

    public List<SurveyResponse> getAllSurveys() {
        return surveyService.getAllSurveys();// filter from aproved and pending.
    }
    public List<SurveyResponse> getAllApprovedSurveys(String username){
        Annotator annotator = annotatorRepository.findById(username).get();
        List<SurveyResponse> surveyResponseList = new ArrayList<>();
        for(CodeSurvey cs: annotator.getApprovedCodeSurvey()){
            surveyResponseList.add(surveyService.makeSurveyResponse(cs));
        }
        return surveyResponseList;
    }

    public List<SurveyResponse> getAllPendingSurveys(String username) {
        Annotator annotator = annotatorRepository.findById(username).get();
        List<SurveyResponse> surveyResponseList = new ArrayList<>();
        for(CodeSurvey cs: annotator.getPendingApprovalsSurveys()){
            surveyResponseList.add(surveyService.makeSurveyResponse(cs));
        }
        return surveyResponseList;
    }

    public void tagSnippetWithAnnotations(String annotatorUsername, Long surveyId, Long snippetId, List<AnnotationResponse> annotationsTag) {
        Set<CodeAnnotations> codeAnnotationsSet = annotationService.getAllCodeAnnotations(annotationsTag);
        CodeSnippet codeSnippet = snippetService.getCodeSnippet(snippetId);

        Set<CodeAnnotations> snippetTags = codeSnippet.getTags();
        for(CodeAnnotations codeAnnotations: codeAnnotationsSet){
            snippetTags.add(codeAnnotations);
        }
        codeSnippet.setTags(snippetTags);

        snippetService.updateSnippet(codeSnippet);
    }
}
