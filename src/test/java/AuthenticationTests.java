import com.csci5308.codeLabeller.Models.DTO.AuthResponse;
import com.csci5308.codeLabeller.Models.DTO.UserLoginDetails;
import com.csci5308.codeLabeller.Models.DTO.UserSignUpDetails;
import com.csci5308.codeLabeller.Repsoitory.AnnotatorRepository;
import com.csci5308.codeLabeller.Repsoitory.UserSignUpRepository;
import com.csci5308.codeLabeller.Service.UserSignUpService;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

public class AuthenticationTests {

    @Test
    public void adminSignupTest(){
        UserSignUpRepository userSignUpRepository = Mockito.mock(UserSignUpRepository.class);
        AnnotatorRepository annotatorRepository = Mockito.mock(AnnotatorRepository.class);


    }

    @Test
    public void adminLoginTest(){
        UserLoginDetails user = new UserLoginDetails("1234","ahanda");
        AuthResponse response = new AuthResponse("token");
        UserSignUpService userSignUpService = Mockito.mock(UserSignUpService.class);
        Mockito.when(userSignUpService.authenticate(user)).thenReturn(response);
    }

}
