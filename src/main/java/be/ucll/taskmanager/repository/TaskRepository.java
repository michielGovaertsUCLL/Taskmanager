package be.ucll.taskmanager.repository;

import be.ucll.taskmanager.domain.Task;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    private List<Task> taskList;

    public TaskRepository(){
        this.taskList = new ArrayList<>();

    }

    public List<Task> getTaskList(){
        return this.taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }
}
