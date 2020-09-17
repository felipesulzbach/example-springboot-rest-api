package restapi.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import restapi.ExampleSpringbootRestApiApplication;
import restapi.util.FormatUtil.EnumMask;

/**
 * @autor: Felipe Sulzbach
 */
@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: util :: FormatUtil")
public class FormatUtilTest {

    @InjectMocks
    private FormatUtil util;

    @Test
    @DisplayName("formatNumber (CNPJ)")
    @SuppressWarnings("static-access")
    public void formatNumberCNPJ() {
        try {
            String value = "12345678901234";
            EnumMask mask = EnumMask.CNPJ;
            String response = util.formatNumber(value, mask);

            Assertions.assertNotNull(response);
            Assertions.assertEquals("12.345.678/9012-34", response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("formatNumber (CPF)")
    @SuppressWarnings("static-access")
    public void formatNumberCPF() {
        try {
            String value = "12345678901";
            EnumMask mask = EnumMask.CPF;
            String response = util.formatNumber(value, mask);

            Assertions.assertNotNull(response);
            Assertions.assertEquals("123.456.789-01", response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
