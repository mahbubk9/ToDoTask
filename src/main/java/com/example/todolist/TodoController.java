package com.example.todolist;

import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    //this.service.saveAll(List.of(new ToDo(04,"Meeting with Boss","High","2023-03-22","Not Done")))
  }
 
  @GetMapping(path="/ToDoTbl")
  public String getAllToDos(Model model) 
  {
    List<ToDo> list = service.getAllToDos();
 
    model.addAttribute("ToDos", list);
    return "ToDoTbl";
  }
 
  @RequestMapping(path = {"/edit", "/edit/{id}"})
  public String editToDoById(Model model, @PathVariable("id") Optional<Integer> id) 
              throws RecordNotFoundException 
  {
    if (id.isPresent()) {
      ToDo entity = service.getToDoById(id.get());
      model.addAttribute("ToDo", entity);
    } else {
      model.addAttribute("ToDo", new ToDo());
    }
    return "add-edit-employee";
  }
   
  @RequestMapping(path = "/delete/{id}")
  public String deleteToDoById(Model model, @PathVariable("id") Integer id) 
              throws RecordNotFoundException 
  {
    service.deleteToDoById(id);
    return "redirect:/";
  }
 
  @RequestMapping(path = "/AddToDo", method = RequestMethod.POST)
  public String createOrUpdateToDo(ToDo todo) 
  {
    service.createOrUpdateToDo(todo);
    return "redirect:/";
  }
  
}