package ServiceTests;

import com.csci5308.codeLabeller.Models.CodeSnippet;
import com.csci5308.codeLabeller.Models.DTO.StartSurveyResponse;
import com.csci5308.codeLabeller.Service.StartSurveyService;
import com.csci5308.codeLabeller.Service.SurveyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class StartSurveyServiceTests {

    @Mock
    SurveyService surveyService;

    @InjectMocks
    StartSurveyService startSurveyService;

    @Test
    public void startTheSurveyTest(){
        int page = 1;
        long surveyId = 1;

        startSurveyService.startTheSurvey(surveyId,page);

        Mockito.verify(surveyService).startSurvey(surveyId,page);
    }

}
