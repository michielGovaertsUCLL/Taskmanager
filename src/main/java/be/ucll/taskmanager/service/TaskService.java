package be.ucll.taskmanager.service;

import be.ucll.taskmanager.domain.Task;
import be.ucll.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        System.out.println("kak");
        throw new NullPointerException("no task with this id " + id);
    }

    //adds a new task to the repository
    public void addTask(Task task) {
        repository.addTask(task);
    }

    //update existing task
    public void editTask(int id, Task task) {
        Task t =  getTask(id);
        t.setTitle(task.title);
        t.setDescription(task.description);
        t.setDueDateTime(task.dueDateTime);
    }

    public void addSubtask(int taskId, Task sub){
        repository.addSubtask(getTask(taskId), sub);
    }

    public void removeSubtask(int taskId, int id){
        repository.removeSubtask(getTask(taskId), id);
    }

    public void getSubTask(int taskId, int id){
        repository.getSubTask(getTask(id), id);
    }


}
