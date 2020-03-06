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

    //go to task creation form page
    @GetMapping("/new")
    public String getTaskForm(Model model){
        model.addAttribute("task", new Task());
        return "taskcreation";
    }

    //create new task form submission
    @PostMapping("/new")
    public String createTask(@Valid @ModelAttribute(value = "task") Task task, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "taskcreation";
        }
        service.addTask(task);
        return "redirect:/tasks";
    }

    //go to edit task form page
    //param: id = task id
    //TODO: this one is close to the same as the task creation page -> merge html and add vars
    @GetMapping("/edit/{id}")
    public String getEditForm(Model model, @PathVariable int id){
        model.addAttribute("task", service.getTask(id));
        return "taskedit";
    }

    //edit existing task form submission
    //param: id = task id
    @PostMapping("/edit/{id}")
    public String editTask(@Valid @ModelAttribute(value = "task") Task task, BindingResult bindingResult, @PathVariable int id, Model model){
        if(bindingResult.hasErrors()){
            return "taskedit";
        }
        service.editTask(id, task);
        return String.format("redirect:/tasks/%s", id);
    }

    //go to sub task form page
    //param: id = task id
    @GetMapping("/{id}/sub/create")
    public String getCreateSubTask(Model model, @PathVariable int id){
        model.addAttribute("id", id);
        model.addAttribute("task", new Task());
        return "subtaskcreation";
    }

    @PostMapping("/{id}/sub/create")
    public String createSubTask(@Valid @ModelAttribute(value = "subtask") Task task, BindingResult bindingResult, @PathVariable int id, Model model) {
        if(bindingResult.hasErrors()){
            return "subtaskcreation";
        }
        service.addSubtask(id, task);
        return String.format("redirect:/tasks/%s", id);
    }
}
