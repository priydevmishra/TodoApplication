package com.example.TODOApplication.TODO_App;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TODOController {
    @Autowired
    TODOService todoService;

    @RequestMapping("/")
    public String getAllTodo(Model model){
        List<Todo> listOfTodos = todoService.getAllTodos();
        model.addAttribute("listoftodos", listOfTodos);
        return "task";
    }

    @RequestMapping(value="/addtodo", method=RequestMethod.POST)
    //@ResponseBody
    public String createTODO(@ModelAttribute Todo todo){
        todoService.saveTodo(todo);
        return "redirect:/";
    }

    @RequestMapping(value="/updatetodo/{id}", method=RequestMethod.POST)
    public String updateTodo(@PathVariable("id") Long id, @ModelAttribute Todo todo){
        todoService.updateTodo(id,todo);
        return"redirect:/";
    }

    @RequestMapping("/deletetodo/{id}")
    public String deleteTodo(@PathVariable("id") Long id){
        todoService.removeTodo(id);
        return "redirect:/";
    }

    // public String getAllTODO(){

    // }
}