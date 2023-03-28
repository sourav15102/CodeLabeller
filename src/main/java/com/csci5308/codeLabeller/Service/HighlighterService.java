package com.csci5308.codeLabeller.Service;

import com.csci5308.codeLabeller.Models.CodeAnnotations;
import com.csci5308.codeLabeller.Models.CodeHighlights;
import com.csci5308.codeLabeller.Models.CodeSnippet;
import com.csci5308.codeLabeller.Models.DTO.AnnotationResponse;
import com.csci5308.codeLabeller.Models.DTO.CodeHighlightResponse;
import com.csci5308.codeLabeller.Repsoitory.HighlighterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HighlighterService {

    @Autowired
    AnnotationService annotationService;

    @Autowired
    HighlighterRepository highlighterRepository;

    public Set<CodeHighlights> getAllHighlights(String annotatorUsername, CodeSnippet codeSnippet, List<CodeHighlightResponse> codeHighlightResponseList){
        Set<CodeHighlights> codeHighlightsSet = new HashSet<>();

        for (CodeHighlightResponse chr: codeHighlightResponseList){
            CodeHighlights codeHighlights = new CodeHighlights();
            codeHighlights.setAnnotated_by(annotatorUsername);
            codeHighlights.setCodeSnippet(codeSnippet);
            codeHighlights.setSpan_start_id(chr.getSpan_start_id());
            codeHighlights.setSpan_end_id(chr.getSpan_end_id());
            codeHighlights.setAnnotation(annotationService.getCodeAnnotation(chr.getAnnotationResponse()));
            codeHighlights = highlighterRepository.save(codeHighlights);
            codeHighlightsSet.add(codeHighlights);
        }
        return codeHighlightsSet;
    }

    public CodeHighlightResponse makeHighlightResponse(CodeHighlights codeHighlights){
        CodeHighlightResponse chr = new CodeHighlightResponse();
        chr.setSpan_start_id(codeHighlights.getSpan_start_id());
        chr.setSpan_end_id(codeHighlights.getSpan_end_id());
        chr.setAnnotated_by(codeHighlights.getAnnotated_by());
        chr.setAnnotationResponse(annotationService.makeAnnotationResponse(codeHighlights.getAnnotation()));
        return chr;
    }

    public Set<CodeHighlightResponse> makeAllHighlightResponse(Set<CodeHighlights> codeHighlightsSet){
        Set<CodeHighlightResponse> chrSet = new HashSet<>();
        for(CodeHighlights codeHighlights: codeHighlightsSet){
            chrSet.add(this.makeHighlightResponse(codeHighlights));
        }

        return chrSet;
    }


}
