package restapi.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import restapi.ExampleSpringbootRestApiApplication;
import restapi.models.resources.ProfileReq;
import restapi.models.resources.ProfileResp;
import restapi.service.ProfileService;
import restapi.util.dataFake.model.resources.ProfileReqDf;
import restapi.util.dataFake.model.resources.ProfileRespDf;

/**
 * @autor: Felipe Sulzbach
 */
@SpringBootTest(classes = { ExampleSpringbootRestApiApplication.class })
@RunWith(SpringRunner.class)
@DisplayName("restapi :: controller :: ProfileController")
public class ProfileControllerTest {

    @Mock
    private ProfileService serviceMock;

    @InjectMocks
    private ProfileController controler;

    @Autowired
    private ProfileRespDf respDf;

    @Autowired
    private ProfileReqDf reqDf;

    private List<ProfileResp> list;
    private ProfileResp obj;

    @BeforeEach
    void before() {
        list = new ArrayList<>();
        list.addAll(respDf.getDataList(5));
        obj = respDf.getData();
    }

    @Test
    @DisplayName("getAll")
    public void getAll() {
        try {
            Mockito.when(serviceMock.getAll()).thenReturn(list);

            ResponseEntity<List<ProfileResp>> responseList = controler.getAll();

            Assertions.assertNotNull(responseList);
            Assertions.assertEquals(responseList.getStatusCodeValue(), 200);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("getById")
    public void getById() {
        try {
            Mockito.when(serviceMock.getById(Mockito.anyLong())).thenReturn(obj);

            Long id = 1L;
            ResponseEntity<ProfileResp> response = controler.getById(id);

            Assertions.assertNotNull(response);
            Assertions.assertEquals(response.getStatusCodeValue(), 200);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("create")
    public void create() {
        try {
            Mockito.when(serviceMock.create(Mockito.any(ProfileReq.class))).thenReturn(obj);

            ResponseEntity<ProfileResp> response = controler.create(reqDf.getData());

            Assertions.assertNotNull(response);
            Assertions.assertEquals(response.getStatusCodeValue(), 201);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("update")
    public void update() {
        try {
            Mockito.when(serviceMock.update(Mockito.anyLong(), Mockito.any(ProfileReq.class))).thenReturn(obj);

            Long id = 1L;
            ResponseEntity<ProfileResp> response = controler.update(id, reqDf.getData());

            Assertions.assertNotNull(response);
            Assertions.assertEquals(response.getStatusCodeValue(), 200);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("remove")
    public void remove() {
        try {
            Mockito.doNothing().when(serviceMock).remove(Mockito.anyLong());

            Long id = 1L;
            controler.remove(id);

            Mockito.verify(serviceMock, Mockito.atLeast(1)).remove(Mockito.anyLong());
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
