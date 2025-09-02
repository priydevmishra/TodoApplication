package com.example.TODOApplication.TODO_App;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class TODORepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Todo> findAll(){
        TypedQuery<Todo> typedQuery = entityManager.createQuery("from Todo", Todo.class);
        return typedQuery.getResultList();
    }

    @Transactional
    public void save(Todo todo){
        entityManager.persist(todo);
    }

    public Optional<Todo> findTodoById(Long id){
        Todo todo = entityManager.find(Todo.class, id);
        return Optional.ofNullable(todo);
    }

    @Transactional
    public void updateTodo(Todo oldtodo){
        Todo todo = entityManager.find(Todo.class, oldtodo.getId());
        todo.setTaskContent(oldtodo.getTaskContent());
        todo.setCompletionStatus(oldtodo.getCompletionStatus());
        entityManager.merge(todo);
    }

    @Transactional
    public void deleteTodoById(Long id){
        Todo todo = entityManager.find(Todo.class, id);
        if(todo!=null){
            entityManager.remove(todo);
        }
    }
}