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
import restapi.models.resources.CourseReq;
import restapi.models.resources.CourseResp;
import restapi.models.resources.ErrorResponse;
import restapi.service.CourseService;
import restapi.util.ServiceException;

/**
 * @autor: Felipe Sulzbach
 */
@CrossOrigin(origins = "*") // Releases access to all domains.
@RestController
@Validated
@RequestMapping(value = "/v1")
public class CourseControler {

    @Autowired
    private CourseService service;

    @ApiOperation(value = "Returns a list of Courses.", notes = "${CourseControler.getAll}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Courses found."),
            @ApiResponse(code = 404, message = "No courses found.", response = ErrorResponse.class) })
    @GetMapping(value = "/course")
    public ResponseEntity<List<CourseResp>> getAll() {
        List<CourseResp> response = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Returns a specific Course.", notes = "${CourseControler.getById}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Course found."),
            @ApiResponse(code = 404, message = "Course not found.", response = ErrorResponse.class) })
    @GetMapping(value = "/course/{id}")
    public ResponseEntity<CourseResp> getById(@NotNull @PathVariable(value = "id") long id) throws ServiceException {
        CourseResp response = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Insert a specific Course.", notes = "${CourseControler.create}")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Course created successfully."),
            @ApiResponse(code = 400, message = "Request Body is invalid.", response = ErrorResponse.class) })
    @PostMapping(value = "/course")
    public ResponseEntity<CourseResp> create(@Validated @NotNull @RequestBody CourseReq courseReq)
            throws ServiceException {
        CourseResp response = service.create(courseReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Updates a specific Course.", notes = "${CourseControler.update}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Course updated successfully."),
            @ApiResponse(code = 400, message = "Request Body is invalid.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Course not found.", response = ErrorResponse.class) })
    @PutMapping(value = "/course/{id}")
    public ResponseEntity<CourseResp> update(@NotNull @PathVariable(value = "id") long id,
            @Validated @RequestBody CourseReq courseReq) throws ServiceException {
        CourseResp response = service.update(id, courseReq);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Removes a specific Course.", notes = "${CourseControler.remove}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Course removed successfully."),
            @ApiResponse(code = 404, message = "Course not found.", response = ErrorResponse.class) })
    @DeleteMapping(value = "/course/{id}")
    public void remove(@NotNull @PathVariable(value = "id") long id) throws ServiceException {
        service.remove(id);
    }
}
