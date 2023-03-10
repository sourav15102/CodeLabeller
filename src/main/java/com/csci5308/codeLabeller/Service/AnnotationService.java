package com.csci5308.codeLabeller.Service;

import com.csci5308.codeLabeller.Models.DTO.AdminSnippetsAnnotationsDTO;
import com.csci5308.codeLabeller.Models.CodeAnnotations;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Models.DTO.AnnotationResponse;
import com.csci5308.codeLabeller.Models.DTO.SurveyResponse;
import com.csci5308.codeLabeller.Repsoitory.AnnotationsRepository;
import com.csci5308.codeLabeller.Repsoitory.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnotationService {

    @Autowired
    AnnotationsRepository annotationsRepository;
    @Autowired
    SurveyRepository surveyRepository;

    public void createAnnotations(AdminSnippetsAnnotationsDTO asaDTO, CodeSurvey survey) {
        List<CodeAnnotations> annotationsList = new ArrayList<>();
        for(String name: asaDTO.getAnnotations()){
            CodeAnnotations codeAnnotation = new CodeAnnotations();
            codeAnnotation.setName(name);
            codeAnnotation.setSurvey(survey);
            annotationsList.add(codeAnnotation);
        }
        annotationsRepository.saveAll(annotationsList);
    }

    public AnnotationResponse makeAnnotationResponse(CodeAnnotations ca) {
        AnnotationResponse annotationResponse = new AnnotationResponse();
        annotationResponse.setAnnotationID(ca.getAnnotationID());
        annotationResponse.setName(ca.getName());
        return annotationResponse;
    }

    public List<AnnotationResponse> getAllAnnotations(String username, Long surveyId) {
        CodeSurvey codeSurvey =  surveyRepository.findById(surveyId).get();
        List<AnnotationResponse> annotationResponseList = new ArrayList<>();

        for(CodeAnnotations ca: codeSurvey.getAnnotationList()){
            annotationResponseList.add(this.makeAnnotationResponse(ca));
        }
        return annotationResponseList;
    }

    public AnnotationResponse getAnnotation(String username, Long surveyId, Long id) {
        CodeAnnotations codeAnnotation = annotationsRepository.findById(id).get();
        return this.makeAnnotationResponse(codeAnnotation);
    }
}
