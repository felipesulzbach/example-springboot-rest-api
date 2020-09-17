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
import restapi.models.Student;
import restapi.models.resources.StudentResp;
import restapi.models.resources.transformer.StudentRespTrans;
import restapi.util.dataFake.model.StudentDf;

/**
 * @autor: Felipe Sulzbach
 */
@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: models :: resources :: transformer :: StudentRespTrans")
public class StudentRespTransTest {

    @InjectMocks
    private StudentRespTrans trasform;

    @Autowired
    private StudentDf entityDf;

    private List<Student> list;
    private Student obj;

    @BeforeEach
    void before() {
        list = new ArrayList<>();
        list.addAll(entityDf.getDataList(5));
        obj = entityDf.getData();
    }

    @Test
    @DisplayName("create")
    @SuppressWarnings("static-access")
    public void create() {
        try {
            StudentRespTrans response = trasform.create();

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("toTransform (Object)")
    public void toTransform() {
        try {
            StudentResp response = trasform.toTransform(obj);

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("toTransform (List)")
    public void toTransformList() {
        try {
            List<StudentResp> responseList = trasform.toTransform(list);

            Assertions.assertNotNull(responseList);
            Assertions.assertTrue(!responseList.isEmpty());
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
