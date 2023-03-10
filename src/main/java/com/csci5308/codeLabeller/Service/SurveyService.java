package com.csci5308.codeLabeller.Service;

import com.csci5308.codeLabeller.Models.CodeAnnotations;
import com.csci5308.codeLabeller.Models.CodeSnippet;
import com.csci5308.codeLabeller.Models.DTO.*;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Repsoitory.AnnotationsRepository;
import com.csci5308.codeLabeller.Repsoitory.SurveyRepository;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;
    @Autowired
    AnnotationService annotationService;
    @Autowired
    SnippetService snippetService;

    public CodeSurvey createSurvey(AdminSnippetsAnnotationsDTO asaDTO){
        CodeSurvey survey = new CodeSurvey();
        survey.setUsername(asaDTO.getUsername());
        survey.setSurveyName(asaDTO.getSurveyName());
        survey.setSurveyLanguage(asaDTO.getSurveyLanguage());

        return surveyRepository.save(survey);
    }

    public List<SurveyResponse> getAllSurveys() {
        List<CodeSurvey> listCodeSurveys = (List<CodeSurvey>) surveyRepository.findAll();
        List<SurveyResponse> list = new ArrayList<>();
        for(CodeSurvey cs: listCodeSurveys){
            list.add(this.makeSurveyResponse(cs));
        }
        return list;
    }
    public List<SurveyResponse> getAllSurveys(String username) {
        List<CodeSurvey> listCodeSurveys = surveyRepository.findAllByUsername(username);
        List<SurveyResponse> list = new ArrayList<>();
        for(CodeSurvey cs: listCodeSurveys){
            list.add(this.makeSurveyResponse(cs));
        }
        return list;
    }

    public SurveyResponse makeSurveyResponse(CodeSurvey cs) {
        Set<AnnotationResponse> annotationResponseSet = new HashSet<>();
        for(CodeAnnotations ca: cs.getAnnotationList()){
            AnnotationResponse ar = annotationService.makeAnnotationResponse(ca);
            annotationResponseSet.add(ar);
        }
        Set<SnippetResponse> snippetResponseSet = new HashSet<>();
        for(CodeSnippet codeSnippet: cs.getSnippetList()){
            SnippetResponse snippetResponse = snippetService.makeSnippetResponse(codeSnippet);
            snippetResponseSet.add(snippetResponse);
        }
        SurveyResponse surveyResponse = new SurveyResponse();
        surveyResponse.setSurveyID(cs.getSurveyID());
        surveyResponse.setSurveyName(cs.getSurveyName());
        surveyResponse.setSurveyLanguage(cs.getSurveyLanguage());
        surveyResponse.setAnnotationResponseSet(annotationResponseSet);
        surveyResponse.setSnippetResponseSet(snippetResponseSet);
        return surveyResponse;
    }

    public SurveyResponse getSurvey(Long surveyID) {
        CodeSurvey survey = surveyRepository.findById(surveyID).get();
        return this.makeSurveyResponse(survey);
    }

}
