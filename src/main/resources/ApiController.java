package com.example.todolist;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller 
@RequestMapping(path="/")

public class ApiController {
    @Autowired private ToDoRepo todoRepo;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewToDo (@RequestParam Integer id,@RequestParam String taskDescription
        , @RequestParam String priority, @RequestParam Date deadline, @RequestParam String status) {
      
      ToDo n = new ToDo();
      n.setId(id);
      n.setTaskDescription(taskDescription);
      n.setPriority(priority);
      n.setDeadLine(deadline);
      n.setStatus(status);
      todoRepo.save(n);
      return "Saved";
    }

    @GetMapping(path="/all")
  public @ResponseBody Iterable<ToDo> getToDos() {
    
    return todoRepo.findAll();
  }
  
    
}
