package com.ramesh.springboot.myfirstwebapp.todo.Repository;

import com.ramesh.springboot.myfirstwebapp.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findByUsername(String username);
}
