package restapi.service.businessRules;

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
import restapi.models.resources.vo.ServiceException;
import restapi.repository.CourseRepository;
import restapi.util.dataFake.model.CourseDf;

/**
 * @autor: Felipe Sulzbach
 */
@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: service :: businessRules :: CourseBr")
public class CourseBrTest {

    @Mock
    private CourseRepository repositoryMock;

    @InjectMocks
    private CourseBr brMock;

    @Autowired
    private CourseDf auDf;

    private Optional<Course> obj;

    @BeforeEach
    void before() {
        obj = Optional.of(auDf.getData());
    }

    @Test
    @DisplayName("validateEntityExists")
    public void validateEntityExists() {
        try {
            Long id = 1L;
            brMock.validateEntityExists(Optional.of(auDf.getData()), id);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("validateEntityExists (ServiceException)")
    public void validateEntityExistsException() {
        try {
            Long id = 1L;
            brMock.validateEntityExists(Optional.empty(), id);
        } catch (ServiceException e) {
            Assertions.assertEquals("ENTITY_NOT_FOUND", e.getCode());
            Assertions.assertTrue(e.getParams() != null && e.getParams().length > 0);
            Assertions.assertEquals(2, e.getParams().length);
            Assertions.assertEquals("1", e.getParams()[0]);
            Assertions.assertEquals("Course", e.getParams()[1]);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("validateNameExists")
    public void validateNameExists() {
        try {
            Mockito.when(repositoryMock.findByName(Mockito.anyString())).thenReturn(null);

            String name = "TEST";
            brMock.validateNameExists(name);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("validateNameExists (ServiceException)")
    public void validateNameExistsException() {
        try {
            Mockito.when(repositoryMock.findByName(Mockito.anyString())).thenReturn(obj);

            String name = "TEST";
            brMock.validateNameExists(name);
        } catch (ServiceException e) {
            Assertions.assertEquals("ENTITY_ALREADY_EXISTS", e.getCode());
            Assertions.assertTrue(e.getParams() != null && e.getParams().length > 0);
            Assertions.assertEquals(2, e.getParams().length);
            Assertions.assertEquals("Course", e.getParams()[0]);
            Assertions.assertEquals("Name TEST", e.getParams()[1]);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
