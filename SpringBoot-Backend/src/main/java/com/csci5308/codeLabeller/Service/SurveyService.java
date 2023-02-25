package com.csci5308.codeLabeller.Service;

import com.csci5308.codeLabeller.Models.DTO.AdminSnippetsAnnotationsDTO;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Repsoitory.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    public CodeSurvey createSurvey(AdminSnippetsAnnotationsDTO asaDTO){
        CodeSurvey survey = new CodeSurvey();
        survey.setUsername(asaDTO.getUsername());
        survey.setSurveyName(asaDTO.getSurveyName());
        survey.setSurveyLanguage(asaDTO.getSurveyLanguage());

        return surveyRepository.save(survey);
    }
}
