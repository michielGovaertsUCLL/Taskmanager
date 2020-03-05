package be.ucll.taskmanager.controller;

import be.ucll.taskmanager.domain.Task;
import be.ucll.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private final TaskService service;

    public TaskController(){
        this.service = new TaskService();
    }

    @GetMapping
    public String getTasks(Model model){
        model.addAttribute("tasks", service.getTasks());
        return "tasks";
    }

    @GetMapping("/{id}")
    public
    String getTaskDetails(Model model, @PathVariable int id){
        model.addAttribute("task", service.getTask(id));
        return "taskdetails";
    }

    @GetMapping("/new")
    public String createTask(Model model){
        model.addAttribute("Task", new Task());
        return "taskcreation";
    }

    @PostMapping("/new")
    public String createTask(Model model, @ModelAttribute @Valid Task task, BindingResult bindingResult){
        service.addTask(task);
        return getTasks(model);
    }
}
