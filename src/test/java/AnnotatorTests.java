import com.csci5308.codeLabeller.Models.DTO.AnnotationResponse;
import com.csci5308.codeLabeller.Models.DTO.StartSurveyResponse;
import com.csci5308.codeLabeller.Models.DTO.SurveyResponse;
import com.csci5308.codeLabeller.Service.AnnotatorService;
import com.csci5308.codeLabeller.Service.StartSurveyService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@SpringBootTest
public class AnnotatorTests {

    @Test
    public void fetchAllSurveysTest(){
        List<SurveyResponse> list = new ArrayList<>();
        SurveyResponse surveyResponse = new SurveyResponse();
        list.add(surveyResponse);

        AnnotatorService annotatorService = Mockito.mock(AnnotatorService.class);
        Mockito.when(annotatorService.getAllSurveys()).thenReturn(list);
    }
    
    @Test
    public void getAllApprovedSurveyTest(){
        List<SurveyResponse> list = new ArrayList<>();
        SurveyResponse surveyResponse = new SurveyResponse();
        list.add(surveyResponse);
        String username = "sghai";

        AnnotatorService annotatorService = Mockito.mock(AnnotatorService.class);
        Mockito.when(annotatorService.getAllApprovedSurveys(username)).thenReturn(list);
    }

    @Test
    public void getAllPendingSurveyTest(){
        List<SurveyResponse> list = new ArrayList<>();
        SurveyResponse surveyResponse = new SurveyResponse();
        list.add(surveyResponse);
        String username = "sghai";

        AnnotatorService annotatorService = Mockito.mock(AnnotatorService.class);
        Mockito.when(annotatorService.getAllPendingSurveys(username)).thenReturn(list);
    }
}
