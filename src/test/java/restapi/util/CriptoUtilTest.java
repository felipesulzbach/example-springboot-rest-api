package restapi.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import restapi.ExampleSpringbootRestApiApplication;
import restapi.util.CriptoUtil.EnumHash;

@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: util :: CriptoUtil")
public class CriptoUtilTest {

    @InjectMocks
    private CriptoUtil util;

    @Test
    @DisplayName("encript")
    @SuppressWarnings("static-access")
    public void encript() {
        try {
            String password = "TEST123";
            String response = util.encript(password, EnumHash.SHA_256);
            Assertions.assertNotNull(response);
            Assertions.assertEquals("56A7010456B474AEEE111F3B7336581FB0A99129D426CF51903EFBDFD629F008", response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
