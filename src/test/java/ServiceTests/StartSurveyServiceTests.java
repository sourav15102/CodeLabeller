package ServiceTests;

import com.csci5308.codeLabeller.Models.CodeSnippet;
import com.csci5308.codeLabeller.Models.DTO.StartSurveyResponse;
import com.csci5308.codeLabeller.Service.StartSurveyService;
import com.csci5308.codeLabeller.Service.SurveyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class StartSurveyServiceTests {

    @Mock
    SurveyService surveyService;

    @InjectMocks
    StartSurveyService startSurveyService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void startTheSurveyTest(){
        int page = 1;
        long surveyId = 1;

        startSurveyService.startTheSurvey(surveyId,page);

        Mockito.verify(surveyService).startSurvey(surveyId,page);
    }

}
