package com.csci5308.codeLabeller.Service;

import com.csci5308.codeLabeller.Models.Annotator;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Models.DTO.SurveyResponse;
import com.csci5308.codeLabeller.Repsoitory.AnnotatorRepository;
import com.csci5308.codeLabeller.Repsoitory.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnotatorService {

    @Autowired
    AnnotatorRepository annotatorRepository;
    @Autowired
    SurveyService surveyService;

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
}
