package com.ramesh.springboot.myfirstwebapp.todo.controller;

import com.ramesh.springboot.myfirstwebapp.todo.model.Todo;
import com.ramesh.springboot.myfirstwebapp.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        String username = this.getLoggedinUsername();
        List<Todo> todoList = todoService.findByUsername(username);
        modelMap.put("todos", todoList);
        return "listTodos";
    }

    @GetMapping("add-todo")
    public String showNewTodoPage(ModelMap modelMap) {
        String username = (String) modelMap.get("name");
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        modelMap.put("todo", todo);
        return "Todo";
    }

    @PostMapping("add-todo")
    public String addTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "Todo";
        }
        String username = (String) modelMap.get("name");
        todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);

        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping("update-todo")
    public String showUpdateTodoPage(@RequestParam int id, ModelMap modelMap) {
        Todo todo = todoService.findById(id);
        modelMap.put("todo", todo);
        return "Todo";
    }

    @PostMapping("update-todo")
    public String updateTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "Todo";
        }
        String username = (String)modelMap.get("username");
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

    private String getLoggedinUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
