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
import restapi.models.resources.ErrorResponse;
import restapi.models.resources.StudentReq;
import restapi.models.resources.StudentResp;
import restapi.service.StudentService;
import restapi.util.ServiceException;

/**
 * @autor: Felipe Sulzbach
 */
@CrossOrigin(origins = "*") // Releases access to all domains.
@RestController
@Validated
@RequestMapping(value = "/v1")
public class StudentControler {

    @Autowired
    private StudentService service;

    @ApiOperation(value = "Returns a list of Students.", notes = "${StudentControler.getAll}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Students found."),
            @ApiResponse(code = 404, message = "No students found.", response = ErrorResponse.class) })
    @GetMapping(value = "/student")
    public ResponseEntity<List<StudentResp>> getAll() {
        List<StudentResp> response = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Returns a specific Student.", notes = "${StudentControler.getById}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Student found."),
            @ApiResponse(code = 404, message = "Student not found.", response = ErrorResponse.class) })
    @GetMapping(value = "/student/{id}")
    public ResponseEntity<StudentResp> getById(@NotNull @PathVariable(value = "id") long id) throws ServiceException {
        StudentResp response = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Insert a specific Student.", notes = "${StudentControler.create}")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Student created successfully."),
            @ApiResponse(code = 400, message = "Request Body is invalid.", response = ErrorResponse.class) })
    @PostMapping(value = "/student")
    public ResponseEntity<StudentResp> create(@Validated @NotNull @RequestBody StudentReq studentReq)
            throws ServiceException {
        StudentResp response = service.create(studentReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Updates a specific Student.", notes = "${StudentControler.update}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Student updated successfully."),
            @ApiResponse(code = 400, message = "Request Body is invalid.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Student not found.", response = ErrorResponse.class) })
    @PutMapping(value = "/student/{id}")
    public ResponseEntity<StudentResp> update(@NotNull @PathVariable(value = "id") long id,
            @Validated @RequestBody StudentReq studentReq) throws ServiceException {
        StudentResp response = service.update(id, studentReq);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Removes a specific Student.", notes = "${StudentControler.remove}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Student removed successfully."),
            @ApiResponse(code = 404, message = "Student not found.", response = ErrorResponse.class) })
    @DeleteMapping(value = "/student/{id}")
    public void remove(@NotNull @PathVariable(value = "id") long id) throws ServiceException {
        service.remove(id);
    }
}
