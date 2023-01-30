package com.ramesh.springboot.myfirstwebapp.todo.service;

import com.ramesh.springboot.myfirstwebapp.todo.model.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "ramesh", "Learn spring", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "ganesh", "Learn react.js", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "suresh", "Learn full stack ", LocalDate.now().plusYears(1), false));
    }

    public List<Todo> findByUsername(String username) {
        return todos.stream().filter(todo -> todo.getUsername().equals(username)).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean isDone) {
        Todo todo = new Todo(++todosCount, username, description, targetDate, isDone);
        todos.add(todo);
    }

    public void deleteById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        return todos.stream().filter(predicate).findFirst().get();
    }

    public void updateTodo(Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
