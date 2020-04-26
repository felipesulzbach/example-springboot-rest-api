package br.com.restapi.exemplorestapi.controler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.restapi.exemplorestapi.models.User;
import br.com.restapi.exemplorestapi.models.resources.ErrorResponse;
import br.com.restapi.exemplorestapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @autor: Felipe Sulzbach
 */
@RestController
// @CrossOrigin(origins = "/http:dominio") // Release access to a specific
// domain.
@CrossOrigin(origins = "*") // Releases access to all domains.
@Validated
@RequestMapping(value = "/api/user")
@Api(value = "API REST com Swagger")
public class UserControler {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Returns a list of Users.", notes = "${UserControler.getAll}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Users found."),
            @ApiResponse(code = 404, message = "No users found.", response = ErrorResponse.class) })
    @GetMapping(value = "/")
    public List<User> getAll() {
        return userService.getAll();
    }

    @ApiOperation(value = "Returns a specific User.", notes = "${UserControler.getById}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "User found."),
            @ApiResponse(code = 404, message = "User not found.", response = ErrorResponse.class) })
    @GetMapping(value = "/{id}")
    public User getById(@PathVariable(value = "id") long id) {
        return userService.getById(id);
    }

    @ApiOperation(value = "Insert a specific User.", notes = "${UserControler.create}")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "User created successfully."),
            @ApiResponse(code = 400, message = "Request Body is invalid.", response = ErrorResponse.class) })
    @PostMapping(value = "/")
    public User create(@RequestBody User usuario) {
        return userService.createOrUpdate(usuario);
    }

    @ApiOperation(value = "Updates a specific User.", notes = "${UserControler.update}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "User updated successfully."),
            @ApiResponse(code = 400, message = "Request Body is invalid.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "User not found.", response = ErrorResponse.class) })
    @PutMapping(value = "/")
    public User update(@RequestBody User usuario) {
        return userService.createOrUpdate(usuario);
    }

    @ApiOperation(value = "Removes a specific User.", notes = "${UserControler.remove}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "User removed successfully."),
            @ApiResponse(code = 404, message = "User not found.", response = ErrorResponse.class) })
    @DeleteMapping(value = "/{id}")
    public void remove(@PathVariable(value = "id") long id) {
        final User user = getById(id);
        log.info("User removed: " + user.toString());
        userService.remove(user);
    }
}
