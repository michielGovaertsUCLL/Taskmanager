package be.ucll.taskmanager.controller;

import be.ucll.taskmanager.DTO.TaskDTO;
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

    //homepage which displays all tasks given by the taskservice
    @GetMapping
    public String getTasks(Model model){
        model.addAttribute("tasks", service.getTasks());
        return "tasks";
    }

    //details of a certain task page
    //param: id = task id
    @GetMapping("/{id}")
    public String getTaskDetails(Model model, @PathVariable int id){
        model.addAttribute("task", service.getTask(id));
        return "taskdetails";
    }

    //go to task creation page
    @GetMapping("/new")
    public String getTaskForm(Model model){
        model.addAttribute("Task", new Task());
        return "taskcreation";
    }

    //create new task form submission
    @PostMapping("/new")
    public String createTask(Model model, @ModelAttribute @Valid Task task, BindingResult bindingResult){
        service.addTask(task);
        return getTasks(model);
    }

    //go to edit task page
    //param: id = task id
    //TODO: this one is close to the same as the task creation page -> merge html and add vars
    @GetMapping("/edit/{id}")
    public String getEditForm(Model model, @PathVariable int id){
        model.addAttribute("task", service.getTask(id));
        return "taskedit";
    }

    //edit existing task form submission
    //
    @PostMapping("/edit/{id}")
    public String editTask(Model model, @ModelAttribute @Valid TaskDTO task, BindingResult bindingResult, @PathVariable int id){
        service.editTask(id, task);
        return getTaskDetails(model, id);
    }
}
