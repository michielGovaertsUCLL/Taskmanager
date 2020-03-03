package be.ucll.taskmanager.controller;

import be.ucll.taskmanager.DTO.TaskDTO;
import be.ucll.taskmanager.domain.Task;
import be.ucll.taskmanager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private TaskService service;

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
    public String createTask(){
        return "taskcreation";
    }

    @PostMapping("/new")
    public String createTask(Model model, @ModelAttribute TaskDTO task){
        service.addTask(task);
        return getTasks(model);
    }
}
