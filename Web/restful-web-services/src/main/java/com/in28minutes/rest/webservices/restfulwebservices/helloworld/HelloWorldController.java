package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.*;

//Controller
@RestController
public class HelloWorldController {

    //GET
    //URI - /hello-world
    //method - "Hello World"

    @GetMapping(path="/hello-world")
    public String helloWorld() {
        return "HelloWorld!!";
    }

    //hello-world-bean
    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("HelloWorldBean!");
    }

    //hello-world-bean/path-variable//in28Minutes
    @GetMapping(path="/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

}
