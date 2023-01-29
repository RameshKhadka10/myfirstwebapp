package com.ramesh.springboot.myfirstwebapp.todo.service;

import com.ramesh.springboot.myfirstwebapp.todo.model.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo(1, "ramesh", "Learn spring", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(2, "ramesh", "Learn react.js", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(1, "ramesh", "Learn full stack ", LocalDate.now().plusYears(1), false));
    }

    public List<Todo> findByUsername(String username) {
        return todos;
    }
}
