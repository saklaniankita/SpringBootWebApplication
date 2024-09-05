package com.springboot.todo;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    static{
        todos.add(new Todo(++todosCount, "ankita", "Learn AWS", LocalDate.now().plusYears(1L), false));
        todos.add(new Todo(++todosCount, "ankita", "Learn DevOps", LocalDate.now().plusYears(2L), false));
        todos.add(new Todo(++todosCount, "ankita", "Learn Azure", LocalDate.now().plusYears(3L), false));
    }

    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean isDone) {
        logger.info("username in TodoService " + username);
        todos.add(new Todo(++todosCount, username, description, targetDate, isDone));
    }

    public void deleteById(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
