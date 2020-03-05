package be.ucll.taskmanager.service;

import be.ucll.taskmanager.DTO.TaskDTO;
import be.ucll.taskmanager.domain.Task;
import be.ucll.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class TaskService {

    @Autowired
    private final TaskRepository repository;

    public TaskService(){
        this.repository = new TaskRepository();
    }

    //returns list with all tasks
    public List<Task> getTasks() {
        return repository.getTaskList();
    }

    //returns task with given id
    public Task getTask(int id){
        for(Task t : repository.getTaskList()){
            if(t.getId()==id){
                return t;
            }
        }
        throw new NullPointerException("no task with this id");
    }

    //adds a new task to the repository
    public void addTask(Task task) {
        repository.addTask(task);
    }

    public void editTask(int id, TaskDTO task) {
        Task t =  getTask(id);
        t.setTitle(task.title);
        t.setDescription(task.description);
        t.setDueDateTime(task.dueDateTime);
    }
}
