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
import restapi.models.resources.TeacherReq;
import restapi.models.resources.TeacherResp;
import restapi.models.resources.vo.ServiceException;
import restapi.service.TeacherService;

/**
 * @autor: Felipe Sulzbach
 */
@CrossOrigin(origins = "*") // Releases access to all domains.
@RestController
@Validated
@RequestMapping(value = "/v1")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @ApiOperation(value = "Returns a list of Teachers.", notes = "${TeacherControler.getAll}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Teachers found."),
            @ApiResponse(code = 404, message = "No teachers found.", response = ErrorResponse.class) })
    @GetMapping(value = "/teacher")
    public ResponseEntity<List<TeacherResp>> getAll() {
        List<TeacherResp> response = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Returns a specific Teacher.", notes = "${TeacherControler.getById}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Teacher found."),
            @ApiResponse(code = 404, message = "Teacher not found.", response = ErrorResponse.class) })
    @GetMapping(value = "/teacher/{id}")
    public ResponseEntity<TeacherResp> getById(@NotNull @PathVariable(value = "id") long id) throws ServiceException {
        TeacherResp response = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Insert a specific Teacher.", notes = "${TeacherControler.create}")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Teacher created successfully."),
            @ApiResponse(code = 400, message = "Request Body is invalid.", response = ErrorResponse.class) })
    @PostMapping(value = "/teacher")
    public ResponseEntity<TeacherResp> create(@Validated @NotNull @RequestBody TeacherReq teacherReq)
            throws ServiceException {
        TeacherResp response = service.create(teacherReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Updates a specific Teacher.", notes = "${TeacherControler.update}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Teacher updated successfully."),
            @ApiResponse(code = 400, message = "Request Body is invalid.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Teacher not found.", response = ErrorResponse.class) })
    @PutMapping(value = "/teacher/{id}")
    public ResponseEntity<TeacherResp> update(@NotNull @PathVariable(value = "id") long id,
            @Validated @RequestBody TeacherReq teacherReq) throws ServiceException {
        TeacherResp response = service.update(id, teacherReq);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Removes a specific Teacher.", notes = "${TeacherControler.remove}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Teacher removed successfully."),
            @ApiResponse(code = 404, message = "Teacher not found.", response = ErrorResponse.class) })
    @DeleteMapping(value = "/teacher/{id}")
    public void remove(@NotNull @PathVariable(value = "id") long id) throws ServiceException {
        service.remove(id);
    }
}
