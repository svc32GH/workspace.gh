package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //GET /users/{id}
    //retrieveUser(int id)
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findOne(id);
    }

    //input - user's details
    //output - CREATE & Return the created URI
    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        service.save(user);
    }

}
