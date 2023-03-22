import com.csci5308.codeLabeller.Controller.AdminController;
import com.csci5308.codeLabeller.Models.DTO.AnnotationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void AdminControllerTest() throws Exception {
        List<AnnotationResponse> list = new ArrayList<>();
        AnnotationResponse annotationResponse = new AnnotationResponse();
        list.add(annotationResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(list);
//
//        MvcResult result = mockMvc.perform(get("/admin/sghai/survey/1/annotation/all/"))
//                .andExpect(status().isOk())
//                .andExpect();
    }

}
