package com.in28minutes.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService service;

    @RequestMapping(value="/list-todos", method= RequestMethod.GET)
    public String listTodos(ModelMap model) {
        model.addAttribute("todos", service.retrieveTodos("in28Minutes"));
        return "list-todos";
    }

    @RequestMapping(value="/add-todo", method= RequestMethod.GET)
    public String showTodoPage(ModelMap model) {
//        model.addAttribute("todo", new Todo(0, "in28Minutes", "", new Date(), false));
        model.addAttribute("todo", new Todo(0, "in28Minutes", "Default Description", new Date(), false));
        return "todo";
    }

    @RequestMapping(value="/add-todo", method= RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        service.addTodo("in28Minutes", todo.getDesc(), new Date(), false);
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
        todo.setUser("in28Minutes"); //TODO:Remove Hardcoding Later

        service.updateTodo(todo);
        return "redirect:list-todos";
    }

}
