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
import restapi.models.Student;
import restapi.models.resources.StudentResp;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.StudentRepository;
import restapi.service.businessRules.StudentBr;
import restapi.util.dataFake.model.SchoolClassDf;
import restapi.util.dataFake.model.StudentDf;
import restapi.util.dataFake.model.resources.StudentReqDf;

/**
 * @autor: Felipe Sulzbach
 */
@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: service :: StudentService")
public class StudentServiceTest {

    @Mock
    private StudentRepository repositoryMock;

    @Mock
    private StudentBr brMock;

    @Mock
    private SchoolClassService schoolClassServiceMock;

    @InjectMocks
    private StudentService service;

    @Autowired
    private StudentDf entityDf;

    @Autowired
    private StudentReqDf reqDf;

    @Autowired
    private SchoolClassDf schoolClassDf;

    private List<Student> list;
    private Optional<Student> obj;

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

            List<StudentResp> responseList = service.getAll();

            Assertions.assertNotNull(responseList);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("getById")
    public void getById() {
        try {
            mockGetStudent();

            Long id = 1L;
            StudentResp response = service.getById(id);

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("create")
    public void create() {
        try {
            Mockito.when(repositoryMock.save(Mockito.any(Student.class))).thenReturn(entityDf.getData());
            mockGetSchoolClass();

            StudentResp response = service.create(reqDf.getData());

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("update")
    public void update() {
        try {
            mockGetStudent();
            Mockito.when(repositoryMock.save(Mockito.any(Student.class))).thenReturn(entityDf.getData());
            Mockito.doNothing().when(brMock).validateNameExists(Mockito.anyString());
            mockGetSchoolClass();

            Long id = 1L;
            StudentResp response = service.update(id, reqDf.getData());

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("remove")
    public void remove() {
        try {
            mockGetStudent();
            Mockito.doNothing().when(repositoryMock).delete(Mockito.any(Student.class));

            Long id = 1L;
            service.remove(id);

            Mockito.verify(repositoryMock, Mockito.atLeast(1)).delete(Mockito.any(Student.class));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    private void mockGetStudent() throws ServiceException {
        Mockito.when(repositoryMock.findById(Mockito.anyLong())).thenReturn(obj);
        Mockito.doNothing().when(brMock).validateEntityExists(Mockito.any(), Mockito.anyLong());
    }

    private void mockGetSchoolClass() throws ServiceException {
        Mockito.when(schoolClassServiceMock.getSchoolClass(Mockito.anyLong())).thenReturn(schoolClassDf.getData());
    }
}
