package be.ucll.taskmanager.controller;

import be.ucll.taskmanager.dto.TaskDTO;
import be.ucll.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class TaskController {

    @Autowired
    private final TaskService service;

    public TaskController(){
        this.service = new TaskService();
    }

    @GetMapping()
    public String getIndex(){
        return "index";
    }


    //homepage which displays all tasks given by the taskservice
    @GetMapping("/tasks")
    public String getTasks(Model model){
        model.addAttribute("tasks", service.getTasks());
        return "tasks";
    }

    //details of a certain task page
    //param: id = task id
    @GetMapping("/tasks/{id}")
    public String getTaskDetails(Model model, @PathVariable Long id){
        model.addAttribute("task", service.getTask(id));
        return "taskdetails";
    }

    //go to task creation form page
    @GetMapping("/tasks/new")
    public String getTaskForm(Model model){
        model.addAttribute("task", new TaskDTO());
        return "taskcreation";
    }

    //create new task form submission
    @PostMapping("/tasks/new")
    public String createTask(@Valid @ModelAttribute(value = "task") TaskDTO task, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "taskcreation";
        }
        service.addTask(task);
        return "redirect:/tasks";
    }

    //go to edit task form page
    //param: id = task id
    //TODO: this one is close to the same as the task creation page -> merge html and add vars
    @GetMapping("/tasks/edit/{id}")
    public String getEditForm(Model model, @PathVariable Long id){
        model.addAttribute("task", service.getTask(id));
        return "taskedit";
    }

    //edit existing task form submission
    //param: id = task id
    @PostMapping("/tasks/edit/{id}")
    public String editTask(@Valid @ModelAttribute(value = "task") TaskDTO task, BindingResult bindingResult, @PathVariable Long id, Model model){
        if(bindingResult.hasErrors()){
            return "taskedit";
        }
        service.addTask(task);
        return String.format("redirect:/tasks/%s", id);
    }

    @GetMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        service.deleteTask(id);
        return String.format("redirect:/tasks");
    }

    //TODO delete task
    //TODO delete subtask

    //go to sub task form page
    //param: id = task id
    @GetMapping("/tasks/{id}/sub/create")
    public String getCreateSubTask(Model model, @PathVariable Long id){
        model.addAttribute("id", id);
        model.addAttribute("subtask", new TaskDTO());
        return "subtaskcreation";
    }

    @PostMapping("/tasks/{id}/sub/create")
    public String createSubTask(@Valid @ModelAttribute(value = "subtask") TaskDTO subtask, BindingResult bindingResult, @PathVariable Long id, Model model) {
        if(bindingResult.hasErrors()){
            return "subtaskcreation";
        }
        service.addSubtask(id, subtask);
        return String.format("redirect:/tasks/%s", id);
    }

    @GetMapping("/tasks/{id}/sub/{sub_id}/delete")
    public String deleteSubTask(@PathVariable Long id, @PathVariable Long sub_id){
        service.deleteTask(sub_id);
        return String.format("redirect:/tasks/%s", id);
    }
}
