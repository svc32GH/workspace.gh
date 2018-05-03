package com.in28minutes.todo;

import com.in28minutes.exception.ExceptionController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {

    private Log logger = LogFactory.getLog(TodoController.class);

    @Autowired
    TodoService service;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping(value="/list-todos", method= RequestMethod.GET)
    public String listTodos(ModelMap model) {
        model.addAttribute("todos", service.retrieveTodos(retrieveLoggedInUserName()));
        return "list-todos";
    }

    private String retrieveLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
   }

    @RequestMapping(value="/add-todo", method= RequestMethod.GET)
    public String showTodoPage(ModelMap model) {
/*
        throw new RuntimeException("Dummy Exception");
*/
        model.addAttribute("todo", new Todo(0, retrieveLoggedInUserName(), "Default Description", new Date(), false));
        return "todo";

    }

    @RequestMapping(value="/add-todo", method= RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        service.addTodo(retrieveLoggedInUserName(), todo.getDesc(), new Date(), false);
        model.clear();
        return "redirect:list-todos";
    }

    @RequestMapping(value="/delete-todo", method= RequestMethod.GET)
    public String deleteTodo(@RequestParam int id, ModelMap model) {
        service.deleteTodo(id);
        model.clear();
        return "redirect:list-todos";
    }

    @RequestMapping(value="/update-todo", method= RequestMethod.GET)
    public String updateTodo(@RequestParam int id, ModelMap model) {
        Todo todo = service.retrieveTodo(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value="/update-todo", method= RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        todo.setUser(retrieveLoggedInUserName()); //TODO:Remove Hardcoding Later

        service.updateTodo(todo);
        return "redirect:list-todos";
    }

    @ExceptionHandler(value = Exception.class)
    public String handleError(HttpServletRequest req, Exception exception) {
        logger.error("Request: " + req.getRequestURL() + " raised " + exception);
        return "error-specific";
    }

}
