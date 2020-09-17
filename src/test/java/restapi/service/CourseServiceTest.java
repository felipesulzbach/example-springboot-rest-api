package restapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import restapi.ExampleSpringbootRestApiApplication;
import restapi.models.Course;
import restapi.models.resources.CourseResp;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.CourseRepository;
import restapi.service.businessRules.CourseBr;
import restapi.util.dataFake.model.CourseDf;
import restapi.util.dataFake.model.resources.CourseReqDf;

/**
 * @autor: Felipe Sulzbach
 */
@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: service :: CourseService")
public class CourseServiceTest {

    @Mock
    private CourseRepository repositoryMock;

    @Mock
    private CourseBr brMock;

    @Mock
    private PersonService personServiceMock;

    @InjectMocks
    private CourseService service;

    @Autowired
    private CourseDf entityDf;

    @Autowired
    private CourseReqDf reqDf;

    private List<Course> list;
    private Optional<Course> obj;

    @BeforeEach
    void before() {
        list = new ArrayList<>();
        list.addAll(entityDf.getDataList(5));
        obj = Optional.of(entityDf.getData());
    }

    @Test
    @DisplayName("getAll")
    public void getAll() {
        try {
            Mockito.when(repositoryMock.findAll()).thenReturn(list);

            List<CourseResp> responseList = service.getAll();

            Assertions.assertNotNull(responseList);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("getById")
    public void getById() {
        try {
            mockGetCourse();

            Long id = 1L;
            CourseResp response = service.getById(id);

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("create")
    public void create() {
        try {
            Mockito.when(repositoryMock.save(Mockito.any(Course.class))).thenReturn(entityDf.getData());

            CourseResp response = service.create(reqDf.getData());

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("update")
    public void update() {
        try {
            mockGetCourse();
            Mockito.when(repositoryMock.save(Mockito.any(Course.class))).thenReturn(entityDf.getData());
            Mockito.doNothing().when(brMock).validateNameExists(Mockito.anyString());

            Long id = 1L;
            CourseResp response = service.update(id, reqDf.getData());

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("remove")
    public void remove() {
        try {
            mockGetCourse();
            Mockito.doNothing().when(repositoryMock).delete(Mockito.any(Course.class));

            Long id = 1L;
            service.remove(id);

            Mockito.verify(repositoryMock, Mockito.atLeast(1)).delete(Mockito.any(Course.class));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    private void mockGetCourse() throws ServiceException {
        Mockito.when(repositoryMock.findById(Mockito.anyLong())).thenReturn(obj);
        Mockito.doNothing().when(brMock).validateEntityExists(Mockito.any(), Mockito.anyLong());
    }
}
