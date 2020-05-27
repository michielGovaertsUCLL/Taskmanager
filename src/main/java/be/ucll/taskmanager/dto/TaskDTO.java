package be.ucll.taskmanager.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDTO {

    public Long id;

    //title not blank, not null and minimum length of 3
    @Size(min = 3, max = 35)
    @NotBlank
    public String title;

    //description not blank, not null and minimum length of 8
    @Size(min = 8, max = 100)
    @NotBlank
    public String description;

    //date and time when the task should be completed (in ISO standard)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime dueDateTime;

    public TaskDTO parent;

    public List<TaskDTO> subtaskDTOs;

    public TaskDTO(){
        this.subtaskDTOs = new ArrayList<>();
    }

    //checks if this taskDTO is a subtask
    public boolean hasSubtask() {
        return this.subtaskDTOs.size() != 0;
    }

    public void addSubtask(TaskDTO sub){this.subtaskDTOs.add(sub);}

    //
    // getters & setters
    //

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title.length() < 3 || title.length() > 35 || title.isBlank()) throw new IllegalArgumentException("error: bad title");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public List<TaskDTO> getSubtaskDTOs() {
        return subtaskDTOs;
    }

    public void setSubtaskDTOs(List<TaskDTO> subtaskDTOs) {
        this.subtaskDTOs = subtaskDTOs;
    }

    public TaskDTO getParent(){return this.parent;}

    public void setParent(TaskDTO parent){this.parent = parent;}


    //overrides
    @Override
    public boolean equals(Object o){
        if(o == null || !(o instanceof TaskDTO)) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return this.getTitle() == taskDTO.getTitle() && this.getId() == taskDTO.getId();
    }

}
