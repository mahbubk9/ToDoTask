package com.example.todolist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.howtodoinjava.demo.entity.EmployeeEntity;
import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.repository.EmployeeRepository;
 
@Service
public class ToDoService {
   
  @Autowired
  ToDoRepo todoRepo;
   
  public List<ToDo> getAllToDos()
  {
    List<ToDo> result = (List<ToDo>) todoRepo.findAll();
     
    if(result.size() > 0) {
      return result;
    } else {
      return new ArrayList<ToDo>();
    }
  }
   
  public ToDo getToDoId(Long id) throws RecordNotFoundException 
  {
    Optional<ToDo> todo = todoRepo.findById(id);
     
    if(todo.isPresent()) {
      return todo.get();
    } else {
      throw new RecordNotFoundException("No employee record exist for given id");
    }
  }
   
  public ToDo createOrUpdateToDo(ToDo todo) 
  {
    if(todo.getId()  == null) 
    {
      todo = todoRepo.save(todo);
       
      return todo;
    } 
    else
    {
      Optional<ToDo> todoEditSave = todoRepo.findById(todo.getId());
       
      if(todoEditSave.isPresent()) 
      {
        ToDo newToDo = todoEditSave.get();
        newToDo.setTaskDescription(todo.getTaskDescription());
        newToDo.setPriority(todo.getPriority());
        newToDo.setDeadLine(todo.getDeadLine());
        newToDo.setStatus(todo.getStatus());
 
 
        todo = todoRepo.save(newToDo);
         
        return todo;
      } else {
        todo = todoRepo.save(todo);
         
        return todo;
      }
    }
  } 
   
  public void deleteEmployeeById(Long id) throws RecordNotFoundException 
  {
    Optional<ToDo> todo = todoRepo.findById(id);
     
    if(todo.isPresent()) 
    {
      todo.deleteById(id);
    } else {
      throw new RecordNotFoundException("No employee record exist for given id");
    }
  } 
}