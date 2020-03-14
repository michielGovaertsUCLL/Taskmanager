package be.ucll.taskmanager.repository;

import be.ucll.taskmanager.domain.Task;
import be.ucll.taskmanager.dto.TaskDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

/*
@Repository
@EnableJpaRepositories
public class TaskRepository{

    private List<TaskDTO> taskList;
    private LonglastID=-1;

    public TaskRepository(){
        this.taskList = new ArrayList<>();

    }

    public List<TaskDTO> getTaskList(){
        return this.taskList;
    }

    //TODO: deze lijst ga maar tot 100 werke momenteel :(
    public void addTask(TaskDTO task) {
        lastID++;
        task.setId(lastID);

        this.taskList.add(task);
    }


    public void addSubtask(TaskDTO task, TaskDTO sub){
        task.addSubtask(sub);
    }

    public void removeSubtask(TaskDTO task, Longid){
        task.removeSubTask(id);
    }

    public void getSubTask(TaskDTO task, Longid){
        task.getSubTask(id);
    }
}
*/
