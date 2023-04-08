package ServiceTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AnnotationServiceTests {


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

}
