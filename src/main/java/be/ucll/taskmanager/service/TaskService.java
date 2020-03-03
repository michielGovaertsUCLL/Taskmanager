package be.ucll.taskmanager.service;

import be.ucll.taskmanager.DTO.TaskDTO;
import be.ucll.taskmanager.domain.Task;
import be.ucll.taskmanager.repository.TaskRepository;

import java.lang.reflect.Array;
import java.util.*;

public class TaskService {

    private final TaskRepository repository;

    public TaskService(){
        this.repository = new TaskRepository();
    }

    public List<Task> getTasks() {
        return repository.getTaskList();
    }

    public Task getTask(int id){
        for(Task t : repository.getTaskList()){
            if(t.getId()==id){
                return t;
            }
        }
        return null;
    }

    public void addTask(TaskDTO task) {

        //example date March 20 2020 at 10am

        String[] datetime = task.dueDate.split(" ");

        List<String> list = new ArrayList<String>(
                Arrays.asList("Januari", "Februari", "March", "April", "May", "June", "Juli",
                              "August", "September", "October", "November", "December"));
        int month = list.indexOf(datetime[0])+1;
        System.out.println(month);
        Task t = new Task(task.title, task.description,
                          Integer.parseInt(datetime[2]), month, Integer.parseInt(datetime[1]), //date
                          Integer.parseInt(datetime[4].substring(0,2))); //time
        repository.addTask(t);
    }
}
