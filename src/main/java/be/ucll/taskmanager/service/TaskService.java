package be.ucll.taskmanager.service;

import be.ucll.taskmanager.domain.Task;
import be.ucll.taskmanager.dto.TaskDTO;
import be.ucll.taskmanager.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepo repository;


    public TaskService(){

    }

    public List<TaskDTO> getTasks(){
        List<TaskDTO> dtos = new ArrayList<>();
        for(Task t : repository.getMainTasks()){
            dtos.add(taskToDTO(t));
        }
        return dtos;
    }

    //returns list with all tasks
    public List<TaskDTO> getAllTasks() {
        List<TaskDTO> dtos = new ArrayList<>();
        for (Task t : repository.findAll()) {
            dtos.add(taskToDTO(t));
        }
        return dtos;
    }

    //returns task with given id
    public TaskDTO getTask(Long id){
        Optional<Task> t = repository.findById(id);
        return taskToDTO(t.get());
    }

    //adds a new task to the repository
    //or updates task if task id not null
    public void addTask(TaskDTO dto) {
        repository.save(DTOToTask(dto));
    }


    //delete task from repository
    public void deleteTask(Long id) {
        Task t = repository.findById(id).get();
        if(t.getSubtasks().size() > 0){
            for(Task st : t.getSubtasks()){
                repository.delete(st);
            }
        }
        repository.delete(t);
    }


    //adds subtask to task with given id
    public void addSubtask(Long taskId, TaskDTO sub){
        //find and convert tasks
        Task subtask = DTOToTask(sub);
        Task task = repository.findById(taskId).get();



        //create relation between main- and subtask
        subtask.setParent(task);
        task.addSubtask(subtask);

        subtask.setId(taskId + task.getSubtasks().size());


        //save both tasks to database
        repository.save(subtask);
        repository.save(task);
    }


    public TaskDTO taskToDTO(Task t){
        TaskDTO dto = new TaskDTO();
        dto.setId(t.getId());
        dto.setTitle(t.getTitle());
        dto.setDescription(t.getDescription());
        dto.setDueDateTime(t.getDueDateTime());
        if (t.hasSubtask()) {
            for(Task sub : t.subtasks){
                dto.addSubtask(taskToDTO(sub));
            }
        }
        return dto;
    }

    public Task DTOToTask(TaskDTO dto){
        Task t = new Task();
        t.setId(dto.getId());
        t.setTitle(dto.getTitle());
        t.setDescription(dto.getDescription());
        t.setDueDateTime(dto.getDueDateTime());
        if(dto.hasSubtask()) {
            for(TaskDTO sub : dto.subtaskDTOs){
                t.addSubtask(DTOToTask(sub));
            }
        }
        return t;
    }

}
