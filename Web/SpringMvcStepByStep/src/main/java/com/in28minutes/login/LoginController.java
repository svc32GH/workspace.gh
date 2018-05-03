package com.in28minutes.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    private LoginService service;

    @RequestMapping(value="/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello SpringMVC Dummy-2";
    }

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String handleLoginRequest(@RequestParam String name,
                                     @RequestParam String password,
                                     ModelMap model) {
        if (service.validateUser(name, password)) {
            model.put("name", name);
            model.put("password", password);
            return "welcome";
        } else {
            model.put("errorMessage", "Invalid Credentials!!");
            return "login";
        }

    }

}
