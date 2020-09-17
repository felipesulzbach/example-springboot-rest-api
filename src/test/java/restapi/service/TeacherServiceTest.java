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
import restapi.models.Teacher;
import restapi.models.resources.TeacherResp;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.TeacherRepository;
import restapi.service.businessRules.TeacherBr;
import restapi.util.dataFake.model.CourseDf;
import restapi.util.dataFake.model.TeacherDf;
import restapi.util.dataFake.model.resources.TeacherReqDf;

/**
 * @autor: Felipe Sulzbach
 */
@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: service :: TeacherService")
public class TeacherServiceTest {

    @Mock
    private TeacherRepository repositoryMock;

    @Mock
    private TeacherBr brMock;

    @Mock
    private PersonService personServiceMock;

    @Mock
    private CourseService courseServiceMock;

    @InjectMocks
    private TeacherService service;

    @Autowired
    private TeacherDf entityDf;

    @Autowired
    private TeacherReqDf reqDf;

    @Autowired
    private CourseDf courseDf;

    private List<Teacher> list;
    private Optional<Teacher> obj;

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

            List<TeacherResp> responseList = service.getAll();

            Assertions.assertNotNull(responseList);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("getById")
    public void getById() {
        try {
            mockGetTeacher();

            Long id = 1L;
            TeacherResp response = service.getById(id);

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("create")
    public void create() {
        try {
            Mockito.when(repositoryMock.save(Mockito.any(Teacher.class))).thenReturn(entityDf.getData());
            Mockito.doNothing().when(brMock).validateNameExists(Mockito.anyString());
            mockGetCourse();

            TeacherResp response = service.create(reqDf.getData());

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("update")
    public void update() {
        try {
            mockGetTeacher();
            Mockito.when(repositoryMock.save(Mockito.any(Teacher.class))).thenReturn(entityDf.getData());
            Mockito.doNothing().when(brMock).validateNameExists(Mockito.anyString());
            mockGetCourse();

            Long id = 1L;
            TeacherResp response = service.update(id, reqDf.getData());

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("remove")
    public void remove() {
        try {
            mockGetTeacher();
            Mockito.doNothing().when(repositoryMock).delete(Mockito.any(Teacher.class));

            Long id = 1L;
            service.remove(id);

            Mockito.verify(repositoryMock, Mockito.atLeast(1)).delete(Mockito.any(Teacher.class));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    private void mockGetTeacher() throws ServiceException {
        Mockito.when(repositoryMock.findById(Mockito.anyLong())).thenReturn(obj);
        Mockito.doNothing().when(brMock).validateEntityExists(Mockito.any(), Mockito.anyLong());
    }

    private void mockGetCourse() throws ServiceException {
        Mockito.when(courseServiceMock.getCourse(Mockito.anyLong())).thenReturn(courseDf.getData());
    }
}
