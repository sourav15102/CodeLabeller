package ServiceTests;

import com.csci5308.codeLabeller.Models.CodeAnnotations;
import com.csci5308.codeLabeller.Models.CodeHighlights;
import com.csci5308.codeLabeller.Models.CodeSnippet;
import com.csci5308.codeLabeller.Models.DTO.AnnotationResponse;
import com.csci5308.codeLabeller.Models.DTO.CodeHighlightResponse;
import com.csci5308.codeLabeller.Repsoitory.HighlighterRepository;
import com.csci5308.codeLabeller.Service.AnnotationService;
import com.csci5308.codeLabeller.Service.HighlighterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class HighlighterServiceTest {

    @Mock
    AnnotationService annotationService;

    @Mock
    HighlighterRepository highlighterRepository;

    @InjectMocks
    HighlighterService highlighterService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllHighlightsTest(){
        CodeSnippet codeSnippet = new CodeSnippet();
        CodeAnnotations codeAnnotations = new CodeAnnotations();
        CodeHighlights codeHighlights = new CodeHighlights();
        String username = "johndoe";
        CodeHighlightResponse chr = new CodeHighlightResponse();
        chr.setSpan_start_id("1");
        chr.setSpan_end_id("5");
        chr.setAnnotated_by(username);
        codeHighlights.setSpan_start_id("1");
        codeHighlights.setSpan_end_id("5");
        codeHighlights.setAnnotated_by(username);
        AnnotationResponse annotationResponse = Mockito.mock(AnnotationResponse.class);
        chr.setAnnotation(annotationResponse);
        List<CodeHighlightResponse> chrl = new ArrayList<>();
        chrl.add(chr);

        Mockito.when(annotationService.getCodeAnnotation(chr.getAnnotation())).thenReturn(codeAnnotations);
        Mockito.doReturn(codeHighlights).when(highlighterRepository).save(any(CodeHighlights.class));

        Set<CodeHighlights> chs = highlighterService.getAllHighlights(username, codeSnippet, chrl);

        for(CodeHighlights ch: chs){
            Assertions.assertTrue(ch.getAnnotated_by().equals(chr.getAnnotated_by()));
        }

    }
}
