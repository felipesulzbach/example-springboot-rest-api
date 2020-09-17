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
import restapi.models.SchoolClass;
import restapi.models.resources.SchoolClassResp;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.SchoolClassRepository;
import restapi.service.businessRules.SchoolClassBr;
import restapi.util.dataFake.model.CourseDf;
import restapi.util.dataFake.model.SchoolClassDf;
import restapi.util.dataFake.model.resources.SchoolClassReqDf;

/**
 * @autor: Felipe Sulzbach
 */
@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: service :: SchoolClassService")
public class SchoolClassServiceTest {

    @Mock
    private SchoolClassRepository repositoryMock;

    @Mock
    private SchoolClassBr brMock;

    @Mock
    private CourseService courseServiceMock;

    @InjectMocks
    private SchoolClassService service;

    @Autowired
    private SchoolClassDf entityDf;

    @Autowired
    private SchoolClassReqDf reqDf;

    @Autowired
    private CourseDf courseDf;

    private List<SchoolClass> list;
    private Optional<SchoolClass> obj;

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

            List<SchoolClassResp> responseList = service.getAll();

            Assertions.assertNotNull(responseList);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("getById")
    public void getById() {
        try {
            mockGetSchoolClass();

            Long id = 1L;
            SchoolClassResp response = service.getById(id);

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("create")
    public void create() {
        try {
            Mockito.when(repositoryMock.save(Mockito.any(SchoolClass.class))).thenReturn(entityDf.getData());
            mockGetCourse();

            SchoolClassResp response = service.create(reqDf.getData());

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("update")
    public void update() {
        try {
            mockGetSchoolClass();
            Mockito.when(repositoryMock.save(Mockito.any(SchoolClass.class))).thenReturn(entityDf.getData());
            mockGetCourse();

            Long id = 1L;
            SchoolClassResp response = service.update(id, reqDf.getData());

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("remove")
    public void remove() {
        try {
            mockGetSchoolClass();
            Mockito.doNothing().when(repositoryMock).delete(Mockito.any(SchoolClass.class));

            Long id = 1L;
            service.remove(id);

            Mockito.verify(repositoryMock, Mockito.atLeast(1)).delete(Mockito.any(SchoolClass.class));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    private void mockGetSchoolClass() throws ServiceException {
        Mockito.when(repositoryMock.findById(Mockito.anyLong())).thenReturn(obj);
        Mockito.doNothing().when(brMock).validateEntityExists(Mockito.any(), Mockito.anyLong());
    }

    private void mockGetCourse() throws ServiceException {
        Mockito.when(courseServiceMock.getCourse(Mockito.anyLong())).thenReturn(courseDf.getData());
    }
}
