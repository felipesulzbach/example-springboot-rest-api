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
import restapi.models.AppUser;
import restapi.models.resources.AppUserResp;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.AppUserRepository;
import restapi.service.businessRules.AppUserBr;
import restapi.util.dataFake.model.AppUserDf;
import restapi.util.dataFake.model.resources.AppUserReqDf;

@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: service :: AppUserService")
public class AppUserServiceTest {

    @Mock
    private AppUserRepository repositoryMock;

    @Mock
    private AppUserBr brMock;

    @Mock
    private PersonService personServiceMock;

    @Mock
    private ProfileService profileServiceMock;

    @InjectMocks
    private AppUserService service;

    @Autowired
    private AppUserDf auDf;

    @Autowired
    private AppUserReqDf reqDf;

    private List<AppUser> list;
    private Optional<AppUser> obj;

    @BeforeEach
    void before() {
        list = new ArrayList<>();
        list.addAll(auDf.getDataList(5));
        obj = Optional.of(auDf.getData());
    }

    @Test
    @DisplayName("getAll")
    public void getAll() {
        try {
            Mockito.when(repositoryMock.findAll()).thenReturn(list);

            List<AppUserResp> responseList = service.getAll();

            Assertions.assertNotNull(responseList);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("getById")
    public void getById() {
        try {
            mockGetAppUser();

            Long id = 1L;
            AppUserResp response = service.getById(id);

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("create")
    public void create() {
        try {
            Mockito.when(repositoryMock.save(Mockito.any(AppUser.class))).thenReturn(auDf.getData());

            AppUserResp response = service.create(reqDf.getData());

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("update")
    public void update() {
        try {
            mockGetAppUser();
            Mockito.when(repositoryMock.save(Mockito.any(AppUser.class))).thenReturn(auDf.getData());
            Mockito.doNothing().when(brMock).validateNameExists(Mockito.anyString());

            Long id = 1L;
            AppUserResp response = service.update(id, reqDf.getData());

            Assertions.assertNotNull(response);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("remove")
    public void remove() {
        try {
            mockGetAppUser();
            Mockito.doNothing().when(repositoryMock).delete(Mockito.any(AppUser.class));

            Long id = 1L;
            service.remove(id);

            Mockito.verify(repositoryMock, Mockito.atLeast(1)).delete(Mockito.any(AppUser.class));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    private void mockGetAppUser() throws ServiceException {
        Mockito.when(repositoryMock.findById(Mockito.anyLong())).thenReturn(obj);
        Mockito.doNothing().when(brMock).validateEntityExists(Mockito.any(), Mockito.anyLong());
    }
}
