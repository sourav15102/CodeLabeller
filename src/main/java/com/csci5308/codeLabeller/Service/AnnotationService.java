package com.csci5308.codeLabeller.Service;

import com.csci5308.codeLabeller.Models.AdminSnippetsAnnotationsDTO;
import com.csci5308.codeLabeller.Models.CodeAnnotations;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Repsoitory.AnnotationsRepository;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnotationService {

    @Autowired
    AnnotationsRepository annotationsRepository;

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

}
