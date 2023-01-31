package com.ramesh.springboot.myfirstwebapp.todo.controller;

import com.ramesh.springboot.myfirstwebapp.todo.Repository.TodoRepository;
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
public class TodoJpaController {
    private TodoRepository todoRepository;

    @Autowired
    public TodoJpaController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("list-todos")
    public String listAllTodos(ModelMap modelMap) {
        String username = this.getLoggedinUsername();
        List<Todo> todoList = todoRepository.findByUsername(username);
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
        todo.setUsername(username);
        todoRepository.save(todo);

        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping("update-todo")
    public String showUpdateTodoPage(@RequestParam int id, ModelMap modelMap) {
        Todo todo = todoRepository.findById(id).get();
        modelMap.put("todo", todo);
        return "Todo";
    }

    @PostMapping("update-todo")
    public String updateTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "Todo";
        }
        String username = (String) modelMap.get("name");
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }

    private String getLoggedinUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
