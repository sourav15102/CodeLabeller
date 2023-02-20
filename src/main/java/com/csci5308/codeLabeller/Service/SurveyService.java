package com.csci5308.codeLabeller.Service;

import com.csci5308.codeLabeller.Models.AdminSnippetsAnnotationsDTO;
import com.csci5308.codeLabeller.Models.CodeAnnotations;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Repsoitory.SurveyRepository;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    public CodeSurvey createSurvey(AdminSnippetsAnnotationsDTO asaDTO){
        CodeSurvey survey = new CodeSurvey();
        survey.setSurveyName(asaDTO.getSurveyName());
        survey.setSurveyLanguage(asaDTO.getSurveyLanguage());

        return surveyRepository.save(survey);
    }
}
