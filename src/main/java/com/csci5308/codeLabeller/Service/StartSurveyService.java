package com.csci5308.codeLabeller.Service;

import com.csci5308.codeLabeller.Models.CodeAnnotations;
import com.csci5308.codeLabeller.Models.CodeSnippet;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Models.DTO.AnnotationResponse;
import com.csci5308.codeLabeller.Models.DTO.StartSurveyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StartSurveyService {

    @Autowired
    SurveyService surveyService;
    @Autowired
    AnnotationService annotationService;
    public StartSurveyResponse startTheSurvey(Long surveyID){
        CodeSurvey survey = surveyService.getCodeSurvey(surveyID);
        CodeSnippet snippet = getIndexSnippet(survey.getSnippetList(),0);
        List<AnnotationResponse> surveyAnnotationList = annotationService.makeListAnnotationResponse(survey.getAnnotationList());
        StartSurveyResponse response = new StartSurveyResponse(snippet.getCodeSnippetId(), snippet.getSnippetText(), surveyAnnotationList);
        return response;
    }

    private CodeSnippet getIndexSnippet(Set<CodeSnippet> snippetSet, int index){
        List<CodeSnippet> list = new ArrayList<>(snippetSet);
        Collections.sort(list, Comparator.comparingLong(CodeSnippet::getCodeSnippetId));
        CodeSnippet snippet = list.get(index);
        return snippet;
    }
}
