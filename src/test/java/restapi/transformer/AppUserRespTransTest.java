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
import restapi.models.resources.transformer.AppUserRespTrans;
import restapi.util.dataFake.model.AppUserDf;

@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: models :: resources :: transformer :: AppUserRespTrans")
public class AppUserRespTransTest {

    @InjectMocks
    private AppUserRespTrans trasform;

    @Autowired
    private AppUserDf auDf;

    private List<AppUser> list;
    private AppUser obj;

    @BeforeEach
    void before() {
        list = new ArrayList<>();
        list.addAll(auDf.getDataList(5));
        obj = auDf.getData();
    }

    @Test
    @DisplayName("create")
    @SuppressWarnings("static-access")
    public void create() {
        try {
            AppUserRespTrans response = trasform.create();
            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("toTransform (Object)")
    public void toTransform() {
        try {
            AppUserResp response = trasform.toTransform(obj);
            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("toTransform (List)")
    public void toTransformList() {
        try {
            List<AppUserResp> responseList = trasform.toTransform(list);
            Assertions.assertNotNull(responseList);
            Assertions.assertTrue(!responseList.isEmpty());
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
