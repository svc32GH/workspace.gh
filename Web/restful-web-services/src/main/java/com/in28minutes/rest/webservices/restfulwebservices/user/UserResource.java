package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.web.bind.annotation.RestController;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    //GET  /users
    //retrieveAllUsers
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/one-user/{id}")
    public User rerieveSingleUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user.getId() == 0) throw new UserNotFoundException("id="+id);
        return user;
    }

    //GET /users/{id}
    //retrieveUser(int id)
    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user.getId() == 0) throw new UserNotFoundException("id="+id);

        //HATEOAS
        //"all-users", SERVER_PATH + "/users"
        //retrieveAllUsers
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if (user.getId() == 0)
            throw new UserNotFoundException("id="+id);
    }

    //input - user's details
    //output - CREATE & Return the created URI
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        // CREATED
        // /user/4     --> savedUser.getId
        // /user/{id}  --> savedUser.getId
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
