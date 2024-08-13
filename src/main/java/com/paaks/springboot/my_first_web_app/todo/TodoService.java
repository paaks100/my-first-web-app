package com.paaks.springboot.my_first_web_app.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    private static List<Todo> todos =new ArrayList<>();

    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "paaks", "Get AWS Certified 1", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "paaks", "Learn DevOps 1", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "paaks", "Learn Full Stack Development 1", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username) {
        return todos.stream()
                    .filter(todo -> todo.getUsername().equals(username))
                    .toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++todosCount, username, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteById(int id) {
        todos.removeIf(todo -> todo.getId() == id);
    }

    public Todo findById(int id) {
        return todos.stream()
                    .filter(todo -> todo.getId() == id)
                    .findFirst()
                    .get();
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById((todo.getId()));
        todos.add(todo);
    }
}
