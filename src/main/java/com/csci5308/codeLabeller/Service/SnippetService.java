package com.csci5308.codeLabeller.Service;

import com.csci5308.codeLabeller.Models.CodeAnnotations;
import com.csci5308.codeLabeller.Models.DTO.AdminSnippetsAnnotationsDTO;
import com.csci5308.codeLabeller.Models.CodeSnippet;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Models.DTO.AnnotationResponse;
import com.csci5308.codeLabeller.Models.DTO.SnippetResponse;
import com.csci5308.codeLabeller.Repsoitory.SnippetRepository;
import com.csci5308.codeLabeller.Repsoitory.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SnippetService {

    @Autowired
    SnippetRepository snippetRepository;
    @Autowired
    AnnotationService annotationService;
    @Autowired
    SurveyRepository surveyRepository;

    public void createSnippets(AdminSnippetsAnnotationsDTO asaDTO, CodeSurvey survey) {
        List<byte[]> snippets = asaDTO.getSnippets();
        List<CodeSnippet> codeSnippetList = new ArrayList<>();
        for(byte[] data: snippets){
            CodeSnippet codeSnippet = new CodeSnippet();
            codeSnippet.setSnippetText(data);
            codeSnippet.setSurvey(survey);
            codeSnippetList.add(codeSnippet);
        }
        snippetRepository.saveAll(codeSnippetList);
    }
    public SnippetResponse makeSnippetResponse(CodeSnippet codeSnippet) {
        SnippetResponse snippetResponse = new SnippetResponse();
        snippetResponse.setSnippetID(codeSnippet.getCodeSnippetId());
        snippetResponse.setSnippetText(codeSnippet.getSnippetText());
        Set<AnnotationResponse> annotationResponseList = new HashSet<>();
        for(CodeAnnotations codeAnnotations: codeSnippet.getTags()){
            annotationResponseList.add(annotationService.makeAnnotationResponse(codeAnnotations));
        }
        snippetResponse.setTaggedAnnotations(annotationResponseList);
        return snippetResponse;
    }

    public List<SnippetResponse> getAllSnippets(String username, Long surveyId) {
        CodeSurvey codeSurvey =  surveyRepository.findById(surveyId).get();
        List<SnippetResponse> snippetResponseList = new ArrayList<>();

        for(CodeSnippet cs: codeSurvey.getSnippetList()){
            snippetResponseList.add(this.makeSnippetResponse(cs));
        }
        return snippetResponseList;
    }

    public SnippetResponse getSnippet(String username, Long surveyId, Long id) {
        CodeSnippet codeSnippet = snippetRepository.findById(id).get();
        return this.makeSnippetResponse(codeSnippet);
    }
}
