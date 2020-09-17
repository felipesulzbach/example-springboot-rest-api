package restapi.transformer;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import restapi.ExampleSpringbootRestApiApplication;
import restapi.models.AppUser;
import restapi.models.resources.AppUserResp;
import restapi.models.resources.transformer.AppUserTrans;
import restapi.util.dataFake.model.resources.AppUserRespDf;

/**
 * @autor: Felipe Sulzbach
 */
@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: models :: resources :: transformer :: AppUserTrans")
public class AppUserTransTest {

    @InjectMocks
    private AppUserTrans trasform;

    @Autowired
    private AppUserRespDf respDf;

    private List<AppUserResp> list;
    private AppUserResp obj;

    @BeforeEach
    void before() {
        list = new ArrayList<>();
        list.addAll(respDf.getDataList(5));
        obj = respDf.getData();
    }

    @Test
    @DisplayName("create")
    @SuppressWarnings("static-access")
    public void create() {
        try {
            AppUserTrans response = trasform.create();

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("toTransform (Object)")
    public void toTransform() {
        try {
            AppUser response = trasform.toTransform(obj);

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("toTransform (List)")
    public void toTransformList() {
        try {
            List<AppUser> responseList = trasform.toTransform(list);

            Assertions.assertNotNull(responseList);
            Assertions.assertTrue(!responseList.isEmpty());
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
