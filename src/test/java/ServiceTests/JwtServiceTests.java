//package ServiceTests;
//
//import com.csci5308.codeLabeller.Enums.UserAuthority;
//import com.csci5308.codeLabeller.Service.JwtService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import static org.mockito.ArgumentMatchers.any;
//
//@RunWith(PowerMockRunner.class)
//public class JwtServiceTests {
//
//    @InjectMocks
//    JwtService jwtService;
//
//
//
//    @Test
//    @PrepareForTest(JwtService.class)
//    public void generateTokenTest() throws Exception {
//        Set<GrantedAuthority> grantedAuthorityList = new HashSet<>();
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(UserAuthority.ANNOTATOR.toString());
//        grantedAuthorityList.add(grantedAuthority);
//        UserDetails userDetails = new User("johndoe","1234", grantedAuthorityList);
//        JwtService jwtServiceSpy = Mockito.spy(jwtService);
//        String jwtToken = "1234";
//        PowerMockito.when(jwtServiceSpy,"encodeTheUserWithClaims",userDetails,any(Map.class))
//                .thenReturn(jwtToken);
//
//        String response = jwtServiceSpy.generateToken(userDetails);
//
//        Assertions.assertTrue(response.equals(jwtToken));
//    }
//}
