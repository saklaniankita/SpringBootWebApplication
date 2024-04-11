package com.in28minutes.springboot.springbootwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

//@Controller
//@SessionAttributes("name")
public class TodoController {
    @Autowired
    private TodoService todoService;

//    public TodoController(TodoService todoService) {
//        super();
//        this.todoService = todoService;
//    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        //String username = (String)model.get("name");
        String username = getLoggedInUsername();
        System.out.println("Logged in user: " + username);
        List<Todo> todos = todoService.findByUsername(username);
        model.put("todos", todos);
        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
       // String username = (String) model.get("name");
        String username = getLoggedInUsername();
        Todo todo = new Todo(0, username, "Enter description", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "todo";
    }

//    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
//    public String addToDo(@RequestParam String description, ModelMap map){
//        String username = (String)map.get("name");
//        System.out.println("username in TodoController: "+username);
//        todoService.addTodo(username, description, LocalDate.now().plusYears(1), false);
//        return "redirect:list-todos";
//    }

    /*Using Command Bean/Backing Object Concept here instead of
     * using @RequestParam for all the fields in the bean
     * @param map
     * @return
     */
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addToDo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        //String username = (String) model.get("name");
        String username = getLoggedInUsername();
        System.out.println("username in TodoController: " + username);
        todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteById(id);
        return "redirect:list-todos";

    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateToDoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.put("todo", todo);
        System.out.println("1");
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateToDo(ModelMap model, @Valid Todo todo, BindingResult result) {
        System.out.println("2");
        if (result.hasErrors()) {
            return "todo";
        }
        //String username = (String) model.get("name");
        String username = getLoggedInUsername();
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

    private String getLoggedInUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();

    }
}