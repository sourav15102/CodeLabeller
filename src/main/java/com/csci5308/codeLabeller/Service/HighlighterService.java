package com.csci5308.codeLabeller.Service;

import com.csci5308.codeLabeller.Models.CodeAnnotations;
import com.csci5308.codeLabeller.Models.CodeSnippet;
import com.csci5308.codeLabeller.Models.DTO.AnnotationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class HighlighterService {

    @Autowired
    AnnotationService annotationService;

    @Autowired
    SnippetService snippetService;
    public void tagSnippetWithAnnotations(String annotatorUsername, Long surveyId, Long snippetId, List<AnnotationResponse> annotationsTag) {
        Set<CodeAnnotations> codeAnnotationsSet = annotationService.getAllCodeAnnotations(annotationsTag);
        CodeSnippet codeSnippet = snippetService.getCodeSnippet(snippetId);

        Set<CodeAnnotations> snippetTags = codeSnippet.getTags();
        for (CodeAnnotations codeAnnotations : codeAnnotationsSet) {
            snippetTags.add(codeAnnotations);
        }
        codeSnippet.setTags(snippetTags);
    }
}
