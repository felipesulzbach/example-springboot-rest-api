package restapi.controller;

import restapi.models.resources.AppUserResp;
import restapi.service.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AppUserControllerTest {

    @Mock
    private AppUserService service;

    private List<AppUserResp> list;

    @BeforeEach
    void before() {
        list = new ArrayList<>();
        list.add(AppUserResp.create());
    }

    @Test
    @DisplayName("AppUserController > getAll")
    public void getAllTest() {
        when(service.getAll()).thenReturn(list);
        assertEquals(list, service.getAll());
    }

}
