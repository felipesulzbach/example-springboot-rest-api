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
import restapi.models.Profile;
import restapi.models.resources.ProfileResp;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.ProfileRepository;
import restapi.service.businessRules.ProfileBr;
import restapi.util.dataFake.model.ProfileDf;
import restapi.util.dataFake.model.resources.ProfileReqDf;

/**
 * @autor: Felipe Sulzbach
 */
@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: service :: ProfileService")
public class ProfileServiceTest {

    @Mock
    private ProfileRepository repositoryMock;

    @Mock
    private ProfileBr brMock;

    @Mock
    private PersonService personServiceMock;

    @InjectMocks
    private ProfileService service;

    @Autowired
    private ProfileDf entityDf;

    @Autowired
    private ProfileReqDf reqDf;

    private List<Profile> list;
    private Optional<Profile> obj;

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

            List<ProfileResp> responseList = service.getAll();

            Assertions.assertNotNull(responseList);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("getById")
    public void getById() {
        try {
            mockGetProfile();

            Long id = 1L;
            ProfileResp response = service.getById(id);

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("create")
    public void create() {
        try {
            Mockito.when(repositoryMock.save(Mockito.any(Profile.class))).thenReturn(entityDf.getData());

            ProfileResp response = service.create(reqDf.getData());

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("update")
    public void update() {
        try {
            mockGetProfile();
            Mockito.when(repositoryMock.save(Mockito.any(Profile.class))).thenReturn(entityDf.getData());
            Mockito.doNothing().when(brMock).validateNameExists(Mockito.anyString());

            Long id = 1L;
            ProfileResp response = service.update(id, reqDf.getData());

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("remove")
    public void remove() {
        try {
            mockGetProfile();
            Mockito.doNothing().when(repositoryMock).delete(Mockito.any(Profile.class));

            Long id = 1L;
            service.remove(id);

            Mockito.verify(repositoryMock, Mockito.atLeast(1)).delete(Mockito.any(Profile.class));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    private void mockGetProfile() throws ServiceException {
        Mockito.when(repositoryMock.findById(Mockito.anyLong())).thenReturn(obj);
        Mockito.doNothing().when(brMock).validateEntityExists(Mockito.any(), Mockito.anyLong());
    }
}
