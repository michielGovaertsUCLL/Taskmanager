package be.ucll.taskmanager.repository;

import be.ucll.taskmanager.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    private List<Task> taskList;

    public TaskRepository(){
        this.taskList = new ArrayList<>();
        this.taskList.add(new Task("Task 1", "iets",2020, 3, 20, 10));
        this.taskList.add(new Task("Task 2", "iets anders", 2020, 3, 21, 18));
    }

    public List<Task> getTaskList(){
        return this.taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }
}
