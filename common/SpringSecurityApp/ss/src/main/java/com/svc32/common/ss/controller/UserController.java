package com.svc32.common.ss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/getUserRights")
    public String getUserRights() {
        return "You have access to user rights.";
    }

    @GetMapping("/getUserList")
    public String getUserList() {
        return "Here is the list of users.";
    }
}
