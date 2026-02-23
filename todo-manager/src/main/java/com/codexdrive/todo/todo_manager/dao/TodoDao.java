package com.codexdrive.todo.todo_manager.dao;

import com.codexdrive.todo.todo_manager.Helper.Helper;
import com.codexdrive.todo.todo_manager.models.Todo;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TodoDao {

    private static final Logger logger =
            LoggerFactory.getLogger(TodoDao.class);

    private final JdbcTemplate template;

    // Constructor Injection (Best Practice)
    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;
    }

    // Table create after bean initialization
    @PostConstruct
    public void init() {

        String createTable = """
                CREATE TABLE IF NOT EXISTS todos (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    title VARCHAR(100) NOT NULL,
                    content VARCHAR(500),
                    status VARCHAR(10) NOT NULL,
                    addedDate DATETIME,
                    todoDate DATETIME
                )
                """;

        template.execute(createTable);
        logger.info("✅ TODO TABLE READY");
    }

    // Save Todo
    public Todo saveTodo(Todo todo) {

        String insertQuery = """
                INSERT INTO todos
                (title, content, status, addedDate, todoDate)
                VALUES (?, ?, ?, ?, ?)
                """;

        int rows = template.update(
                insertQuery,
                todo.getTitle(),
                todo.getContent(),
                todo.getStatus(),
                todo.getAddedDate(),
                todo.getToDODate()
        );

        logger.info("✅ {} TODO inserted", rows);
        return todo;
    }

    //get single todo from database
    public Todo getTodo(int id) throws ParseException {
        String query = "select * from todos WHERE id = ?";
//        Map<String,Object> todoData = template.queryForMap(query,id);

//        List<Map<String, Object>> results = template.queryForList(query, id);
        Todo todo = template.queryForObject(query, new TodoRowMapper(), id);

//        Todo todo = new Todo();
//
////        todo.setId(((int)todoData.get("id")));
//        todo.setId(((Number) todoData.get("id")).intValue());
//        todo.setTitle(((String)todoData.get("title")));
//        todo.setContent(((String)todoData.get("content")));
//        todo.setStatus(((String)todoData.get("status")));
//        todo.setAddedDate(Helper.parseDate((LocalDateTime) todoData.get("addedDate")));
//        todo.setToDODate(Helper.parseDate((LocalDateTime) todoData.get("todoDate")));
//


        return todo;
    }

    //get all todos from database

    public List<Todo> getAllTodos() {
        String query = "select * from todos";
        List<Todo> todo = template.query(query, new TodoRowMapper());
        return todo;
//        List<Map<String, Object>> maps = template.queryForList(query);

//        List<Todo> todos = maps.stream().map((map) -> {
//            Todo todo = new Todo();
//
//            todo.setId(((int) map.get("id")));
//            todo.setTitle(((String) map.get("title")));
//            todo.setContent(((String) map.get("content")));
//            todo.setStatus(((String) map.get("status")));
//            try {
//                todo.setAddedDate(Helper.parseDate((LocalDateTime) map.get("addedDate")));
//                todo.setToDODate(Helper.parseDate((LocalDateTime) map.get("todoDate")));
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//
//
//            return todo;
//        }).collect(Collectors.toList());
//
//        return todos;
    }


    //update todo
    public Todo updateTodo(int id, Todo newTodo) {
        String query = "update todos set title = ? , content=? , status=? , addedDate=?,todoDate=? WHERE id=?";

        int update = template.update(query, newTodo.getTitle(), newTodo.getContent(), newTodo.getStatus(), newTodo.getAddedDate(), newTodo.getToDODate(), id);
        logger.info("UPDATED{}", update);
        newTodo.setId(id);
        return newTodo;
    }

    //delete todo from database
    public void deleteTodo(int id) {
        String query = " delete from todos WHERE id =?";
        int update = template.update(query, id);
        logger.info("DELETED {}", update);
    }

    public void deleteMultiple(int ids[]) {
        String query = "delete from todos WHERE id = ?";
        //for forEach =>
//        template.batchUpdate(query,ids)
        template.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int id = ids[i];
                ps.setInt(1, id);
            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        });

        for (int i : ids) {
            logger.info("DELETED{} :", i);
        }
    }

}