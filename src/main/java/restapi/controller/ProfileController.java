package restapi.controller;

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
import restapi.models.resources.ErrorResponse;
import restapi.models.resources.ProfileReq;
import restapi.models.resources.ProfileResp;
import restapi.models.resources.vo.ServiceException;
import restapi.service.ProfileService;

/**
 * @autor: Felipe Sulzbach
 */
@CrossOrigin(origins = "*") // Releases access to all domains.
@RestController
@Validated
@RequestMapping(value = "/v1")
public class ProfileController {

    @Autowired
    private ProfileService service;

    @ApiOperation(value = "Returns a list of Profiles.", notes = "${ProfileControler.getAll}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Profiles found."),
            @ApiResponse(code = 404, message = "No profiles found.", response = ErrorResponse.class) })
    @GetMapping(value = "/profile")
    public ResponseEntity<List<ProfileResp>> getAll() {
        List<ProfileResp> response = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Returns a specific Profile.", notes = "${ProfileControler.getById}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Profile found."),
            @ApiResponse(code = 404, message = "Profile not found.", response = ErrorResponse.class) })
    @GetMapping(value = "/profile/{id}")
    public ResponseEntity<ProfileResp> getById(@NotNull @PathVariable(value = "id") long id) throws ServiceException {
        ProfileResp response = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Insert a specific Profile.", notes = "${ProfileControler.create}")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Profile created successfully."),
            @ApiResponse(code = 400, message = "Request Body is invalid.", response = ErrorResponse.class) })
    @PostMapping(value = "/profile")
    public ResponseEntity<ProfileResp> create(@Validated @NotNull @RequestBody ProfileReq profileReq)
            throws ServiceException {
        ProfileResp response = service.create(profileReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Updates a specific Profile.", notes = "${ProfileControler.update}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Profile updated successfully."),
            @ApiResponse(code = 400, message = "Request Body is invalid.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Profile not found.", response = ErrorResponse.class) })
    @PutMapping(value = "/profile/{id}")
    public ResponseEntity<ProfileResp> update(@NotNull @PathVariable(value = "id") long id,
            @Validated @RequestBody ProfileReq profileReq) throws ServiceException {
        ProfileResp response = service.update(id, profileReq);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Removes a specific Profile.", notes = "${ProfileControler.remove}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Profile removed successfully."),
            @ApiResponse(code = 404, message = "Profile not found.", response = ErrorResponse.class) })
    @DeleteMapping(value = "/profile/{id}")
    public void remove(@NotNull @PathVariable(value = "id") long id) throws ServiceException {
        service.remove(id);
    }
}
