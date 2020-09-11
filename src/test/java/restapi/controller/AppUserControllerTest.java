package restapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import restapi.ExampleSpringbootRestApiApplication;
import restapi.models.resources.AppUserResp;
import restapi.service.AppUserService;
import restapi.util.unitTests.dataFake.model.resources.AppUserRespDf;

@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
public class AppUserControllerTest {

    @Mock
    private AppUserService serviceMock;

    @InjectMocks
    private AppUserController controler;

    @Autowired
    private AppUserRespDf dataFake;

    private List<AppUserResp> list;

    @BeforeEach
    void before() {
        list = new ArrayList<>();
        list.addAll(dataFake.getDataList(5));
    }

    @Test
    @DisplayName("AppUserController > getAll")
    public void getAllTest() {
        try {
            when(serviceMock.getAll()).thenReturn(list);

            ResponseEntity<List<AppUserResp>> responseList = controler.getAll();
            assertEquals(responseList.getStatusCodeValue(), 200);
        } catch (Exception e) {
            fail(e);
        }
    }
}
