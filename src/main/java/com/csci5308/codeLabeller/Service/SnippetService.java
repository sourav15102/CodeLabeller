package com.csci5308.codeLabeller.Service;

import com.csci5308.codeLabeller.Models.AdminSnippetsAnnotationsDTO;
import com.csci5308.codeLabeller.Models.CodeSnippet;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Repsoitory.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SnippetService {

    @Autowired
    SnippetRepository snippetRepository;

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
}
