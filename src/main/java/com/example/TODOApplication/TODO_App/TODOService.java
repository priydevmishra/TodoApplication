package com.example.TODOApplication.TODO_App;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TODOService {
    
    @Autowired
    TODORepository todoRepository;

    public void saveTodo(Todo todo){
        todoRepository.save(todo);
    }

    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    public void updateTodo(Long id, Todo newTodo){
        Optional<Todo> todoOldBox = todoRepository.findTodoById(id);
        if(todoOldBox.isPresent()){
            Todo oldTodo = todoOldBox.get();
            oldTodo.setTaskContent(newTodo.getTaskContent());
            oldTodo.setCompletionStatus(newTodo.getCompletionStatus());
            todoRepository.updateTodo(oldTodo);
        }
    }

    public void removeTodo(Long id){
        todoRepository.deleteTodoById(id);
    }
}