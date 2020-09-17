package restapi.util;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import restapi.ExampleSpringbootRestApiApplication;

/**
 * @autor: Felipe Sulzbach
 */
@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: util :: DataFakeUtil")
public class DataFakeUtilTest {

    @InjectMocks
    private DataFakeUtil util;

    @Test
    @DisplayName("getString")
    @SuppressWarnings("static-access")
    public void getString() {
        try {
            int lengthValue = 5;
            boolean useNumbers = true;
            String response = util.getString(lengthValue, useNumbers);

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("getInteger")
    @SuppressWarnings("static-access")
    public void getInteger() {
        try {
            int lengthValue = 5;
            Integer response = util.getInteger(lengthValue);

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("getIntegerString")
    @SuppressWarnings("static-access")
    public void getIntegerString() {
        try {
            int lengthValue = 5;
            String response = util.getIntegerString(lengthValue);

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("getLong")
    @SuppressWarnings("static-access")
    public void getLong() {
        try {
            int lengthValue = 5;
            Long response = util.getLong(lengthValue);

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("getLocalDateTime")
    @SuppressWarnings("static-access")
    public void getLocalDateTime() {
        try {
            LocalDateTime startTime = LocalDateTime.MIN, endTime = LocalDateTime.MAX;
            LocalDateTime response = util.getLocalDateTime(startTime, endTime);

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
