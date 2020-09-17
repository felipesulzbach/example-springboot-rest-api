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
import restapi.models.Person;
import restapi.models.resources.PersonResp;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.PersonRepository;
import restapi.service.businessRules.PersonBr;
import restapi.util.dataFake.model.PersonDf;
import restapi.util.dataFake.model.resources.PersonReqDf;

/**
 * @autor: Felipe Sulzbach
 */
@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: service :: PersonService")
public class PersonServiceTest {

    @Mock
    private PersonRepository repositoryMock;

    @Mock
    private PersonBr brMock;

    @Mock
    private PersonService personServiceMock;

    @InjectMocks
    private PersonService service;

    @Autowired
    private PersonDf entityDf;

    @Autowired
    private PersonReqDf reqDf;

    private List<Person> list;
    private Optional<Person> obj;

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

            List<PersonResp> responseList = service.getAll();

            Assertions.assertNotNull(responseList);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("getById")
    public void getById() {
        try {
            mockGetPerson();

            Long id = 1L;
            PersonResp response = service.getById(id);

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("create")
    public void create() {
        try {
            Mockito.when(repositoryMock.save(Mockito.any(Person.class))).thenReturn(entityDf.getData());

            PersonResp response = service.create(reqDf.getData());

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("update")
    public void update() {
        try {
            mockGetPerson();
            Mockito.when(repositoryMock.save(Mockito.any(Person.class))).thenReturn(entityDf.getData());
            Mockito.doNothing().when(brMock).validateNameExists(Mockito.anyString());

            Long id = 1L;
            PersonResp response = service.update(id, reqDf.getData());

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("remove")
    public void remove() {
        try {
            mockGetPerson();
            Mockito.doNothing().when(repositoryMock).delete(Mockito.any(Person.class));

            Long id = 1L;
            service.remove(id);

            Mockito.verify(repositoryMock, Mockito.atLeast(1)).delete(Mockito.any(Person.class));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    private void mockGetPerson() throws ServiceException {
        Mockito.when(repositoryMock.findById(Mockito.anyLong())).thenReturn(obj);
        Mockito.doNothing().when(brMock).validateEntityExists(Mockito.any(), Mockito.anyLong());
    }
}
