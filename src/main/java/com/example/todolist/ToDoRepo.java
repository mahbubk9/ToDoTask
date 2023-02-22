package com.example.todolist;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepo extends CrudRepository<ToDo,Integer> {
    
}
