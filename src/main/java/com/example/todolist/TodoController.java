package com.example.todolist;

import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.todolist.ToDo;;
 

 
@Controller
@RequestMapping(path="/")
public class TodoController {
  @Autowired
  ToDoService service;

  public TodoController(ToDoService service) {
    this.service = service;
    
  }
 
  @GetMapping(path="/AllToDos")
  public String getAllToDos(Model model) 
  {
    List<ToDo> list = service.getAllToDos();
 
    model.addAttribute("ToDos", list);
    return "AllToDos";
  }

  @PostMapping(path = "/AddToDo")
  public String submit(@ModelAttribute("ToDos") ToDo task,  Model model) {
    model.addAttribute("id", task.getId());
      model.addAttribute("taskDescription", task.getTaskDescription());
      model.addAttribute("priority", task.getPriority());
      model.addAttribute("deadLine", task.getDeadLine());
      model.addAttribute("status", task.getStatus());
      return "AllToDos";
  }
 
  
   
  

 
  
}