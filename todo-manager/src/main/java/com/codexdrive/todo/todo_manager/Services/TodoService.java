package com.codexdrive.todo.todo_manager.Services;

import com.codexdrive.todo.todo_manager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Component
@Service
public class TodoService {

    Logger logger = LoggerFactory.getLogger(TodoService.class);

    List<Todo> todos = new ArrayList<>();

    // create todo
    public Todo createTodo(Todo todo) {
        todos.add(todo);
        logger.info("Todo {}", this.todos);
        return todo;
    }

    // GET ALL TODOS
    public List<Todo> getAllTodos() {
        return todos;
    }

    public Todo getTodo(int todoId) throws ParseException {
        Todo todo = todos.stream().filter(t -> todoId == t.getId()).findAny().get();
        logger.info("TODO : {}", todo);
        return todo;
    }

    public Todo updateTodo(int todoId, Todo todo) {
        List<Todo> newUpdateList = todos.stream().map(t -> {
            if (t.getId() == todoId) {
                //perform update
                t.setTitle(todo.getTitle());
                t.setContent(todo.getContent());
                t.setStatus(todo.getStatus());
                return t;
            } else {
                return t;
            }
        }).collect(Collectors.toList());
        todos = newUpdateList;
        todo.setId(todoId);
        return todo;
    }

    public void deleteTodo(int todoId) {
        logger.info("DELETING TODO");
        List<Todo> newList = todos.stream().filter(t -> t.getId() != todoId).collect(Collectors.toList());
        todos = newList;
    }
}