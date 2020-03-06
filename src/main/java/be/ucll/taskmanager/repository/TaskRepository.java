package be.ucll.taskmanager.repository;

import be.ucll.taskmanager.domain.Task;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    private List<Task> taskList;
    private int lastID=-1;

    public TaskRepository(){
        this.taskList = new ArrayList<>();

    }

    public List<Task> getTaskList(){
        return this.taskList;
    }

    //TODO: deze lijst ga maar tot 100 werke momenteel :(
    public void addTask(Task task) {
        lastID++;
        task.setId(lastID);

        this.taskList.add(task);
    }

    public void addSubtask(Task task, Task sub){
        task.addSubtask(sub);
    }

    public void removeSubtask(Task task, int id){
        task.removeSubTask(id);
    }

    public void getSubTask(Task task, int id){
        task.getSubTask(id);
    }
}
