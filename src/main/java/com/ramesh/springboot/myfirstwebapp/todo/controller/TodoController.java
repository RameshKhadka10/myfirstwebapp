package com.ramesh.springboot.myfirstwebapp.todo.controller;

import com.ramesh.springboot.myfirstwebapp.todo.model.Todo;
import com.ramesh.springboot.myfirstwebapp.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("list-todos")
    public String listAllTodos(ModelMap modelMap) {
        List<Todo> todoList = todoService.findByUsername("ramesh");
        modelMap.put("todos", todoList);
        return "listTodos";
    }
}
