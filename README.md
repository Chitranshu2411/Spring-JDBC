# 🚀 Spring JDBC Todo Manager

This project is a Spring Boot application built using **Spring JDBC (JdbcTemplate)** to perform CRUD operations with a MySQL database.

## 📌 Tech Stack

- Java 17+
- Spring Boot
- Spring JDBC
- MySQL
- Maven
- REST API

---

## 📖 About the Project

This project demonstrates how to use **Spring JDBC** instead of Hibernate/JPA to directly interact with the database using `JdbcTemplate`.

It includes:

- Create Todo
- Read Todo
- Update Todo
- Delete Todo
- Batch Operations
- Custom RowMapper
- Exception Handling

---

## 🛠 Features Implemented

✔ CRUD Operations using JdbcTemplate  
✔ Batch Insert using BatchPreparedStatementSetter  
✔ Logging with SLF4J  
✔ Clean Layered Architecture (Controller → Service → DAO → DB)  
✔ Exception Handling  

---

## 📂 Project Structure
com.codexdrive.todo
│
├── controller
├── service
├── dao
├── models
└── helper


---

## 🧠 Key Concepts Learned

- Difference between JPA and Spring JDBC
- How JdbcTemplate works internally
- RowMapper usage
- Handling EmptyResultDataAccessException
- Writing clean SQL queries
- Batch processing in Spring JDBC

---

## ⚙ Database Configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
