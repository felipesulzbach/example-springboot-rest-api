package restapi.controler;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import restapi.models.resources.AppUserReq;
import restapi.models.resources.AppUserResp;
import restapi.models.resources.ErrorResponse;
import restapi.service.AppUserService;
import restapi.util.ServiceException;

/**
 * @autor: Felipe Sulzbach
 */
@CrossOrigin(origins = "*") // Releases access to all domains.
@RestController
@Validated
@RequestMapping(value = "/v1")
public class AppUserControler {

    @Autowired
    private AppUserService service;

    @ApiOperation(value = "Returns a list of Users.", notes = "${AppUserControler.getAll}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Users found."),
            @ApiResponse(code = 404, message = "No users found.", response = ErrorResponse.class) })
    @GetMapping(value = "/user")
    public ResponseEntity<List<AppUserResp>> getAll() {
        List<AppUserResp> response = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Returns a specific User.", notes = "${UserControler.getById}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "User found."),
            @ApiResponse(code = 404, message = "User not found.", response = ErrorResponse.class) })
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<AppUserResp> getById(@NotNull @PathVariable(value = "id") long id) throws ServiceException {
        AppUserResp response = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Insert a specific User.", notes = "${UserControler.create}")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "User created successfully."),
            @ApiResponse(code = 400, message = "Request Body is invalid.", response = ErrorResponse.class) })
    @PostMapping(value = "/user")
    public ResponseEntity<AppUserResp> create(@Validated @NotNull @RequestBody AppUserReq userReq)
            throws ServiceException {
        AppUserResp response = service.create(userReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Updates a specific User.", notes = "${UserControler.update}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "User updated successfully."),
            @ApiResponse(code = 400, message = "Request Body is invalid.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "User not found.", response = ErrorResponse.class) })
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<AppUserResp> update(@NotNull @PathVariable(value = "id") long id,
            @Validated @RequestBody AppUserReq userReq) throws ServiceException {
        AppUserResp response = service.update(id, userReq);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Removes a specific User.", notes = "${UserControler.remove}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "User removed successfully."),
            @ApiResponse(code = 404, message = "User not found.", response = ErrorResponse.class) })
    @DeleteMapping(value = "/user/{id}")
    public void remove(@NotNull @PathVariable(value = "id") long id) throws ServiceException {
        service.remove(id);
    }
}
