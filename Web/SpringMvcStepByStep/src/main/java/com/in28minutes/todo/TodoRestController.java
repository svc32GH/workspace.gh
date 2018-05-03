package com.in28minutes.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoRestController {
    @Autowired
    TodoService service;

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public List<Todo> retrieveAllTodos() {
        return service.retrieveTodos("in28Minutes");
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    public Todo retrieveTodo(@PathVariable int id) {
        return service.retrieveTodo(id);
    }

}
