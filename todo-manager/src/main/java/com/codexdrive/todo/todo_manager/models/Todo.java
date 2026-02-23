package com.codexdrive.todo.todo_manager.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Todo {

    private int id;
    private String title;
    private String content;
    private String status;
    private Date addedDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date toDODate;

    public Todo ( int id , String title , String content , String status , Date addedDate , Date toDODate){
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.addedDate = addedDate;
        this.toDODate = toDODate;
    }


    public Todo() {}

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getToDODate() {
        return toDODate;
    }

    public void setToDODate(Date toDODate) {
        this.toDODate = toDODate;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", addedDate=" + addedDate +
                ", toDODate=" + toDODate +
                '}';
    }
}