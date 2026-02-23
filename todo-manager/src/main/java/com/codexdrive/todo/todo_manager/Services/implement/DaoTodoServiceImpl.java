package com.codexdrive.todo.todo_manager.Services.implement;

import com.codexdrive.todo.todo_manager.Services.TodoService;
import com.codexdrive.todo.todo_manager.dao.TodoDao;
import com.codexdrive.todo.todo_manager.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
@Primary
public class DaoTodoServiceImpl extends TodoService {

    @Autowired
    private TodoDao todoDao;

    @Override
    public Todo createTodo(Todo todo) {
        return todoDao.saveTodo(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoDao.getAllTodos();
    }

    @Override
    public Todo getTodo(int todoId) throws ParseException {
        return todoDao.getTodo(todoId);
    }


    @Override
    public Todo updateTodo(int todoId, Todo todo) {
        return todoDao.updateTodo(todoId, todo);
    }

    @Override
    public void deleteTodo(int todoId) {
        todoDao.deleteTodo(todoId);
    }
}