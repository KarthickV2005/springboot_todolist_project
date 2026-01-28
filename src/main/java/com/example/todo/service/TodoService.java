package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    
    @Autowired
    private TodoRepository todoRepository;
    
    // CREATE
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }
    
    // READ - Get all todos
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    
    // READ - Get single todo by ID
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }
    
    // UPDATE
    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        
        todo.setText(todoDetails.getText());
        todo.setCompleted(todoDetails.getCompleted());
        
        return todoRepository.save(todo);
    }
    
    // DELETE
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
