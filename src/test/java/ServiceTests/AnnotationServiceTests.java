package ServiceTests;

import com.csci5308.codeLabeller.Models.CodeAnnotations;
import com.csci5308.codeLabeller.Models.CodeSurvey;
import com.csci5308.codeLabeller.Models.DTO.AdminSnippetsAnnotationsDTO;
import com.csci5308.codeLabeller.Repsoitory.AnnotationsRepository;
import com.csci5308.codeLabeller.Service.AnnotationService;
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

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AnnotationServiceTests {
    @Mock
    AnnotationsRepository annotationsRepository;

    @InjectMocks
    AnnotationService annotationService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createAnnotationsTest(){
        AdminSnippetsAnnotationsDTO asaDTO = new AdminSnippetsAnnotationsDTO();
        List<String> stringList = new ArrayList<>(){{
            add("abcd");
        }};
        asaDTO.setAnnotations(stringList);
        CodeSurvey codeSurvey = Mockito.mock(CodeSurvey.class);

        annotationService.createAnnotations(asaDTO,codeSurvey);
        Mockito.verify(annotationsRepository).saveAll(any(List.class));
    }
}