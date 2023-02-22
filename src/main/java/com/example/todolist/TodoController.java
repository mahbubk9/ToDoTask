package com.example.todolist;

import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import com.howtodoinjava.demo.entity.EmployeeEntity;
import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.service.EmployeeService;
 
@Controller
@RequestMapping("/")
public class TodoController {
  @Autowired
  ToDoService service;
 
  @RequestMapping
  public String getAllToDos(ToDo todo) 
  {
    List<ToDo> list = service.getAllToDos();
 
    todo.addAttribute("ToDos", list);
    return "list-Todos";
  }
 
  @RequestMapping(path = {"/edit", "/edit/{id}"})
  public String editToDoById(ToDo todo, @PathVariable("id") Optional<Long> id) 
              throws RecordNotFoundException 
  {
    if (id.isPresent()) {
      ToDo entity = service.getToDoById(id.get());
      model.addAttribute("ToDo", entity);
    } else {
      model.addAttribute("ToDo", new ToDoEntity());
    }
    return "add-edit-employee";
  }
   
  @RequestMapping(path = "/delete/{id}")
  public String deleteEmployeeById(Model model, @PathVariable("id") Long id) 
              throws RecordNotFoundException 
  {
    service.deleteEmployeeById(id);
    return "redirect:/";
  }
 
  @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
  public String createOrUpdateEmploy(ToDo todo) 
  {
    service.createOrUpdateEmployee(todo);
    return "redirect:/";
  }
}