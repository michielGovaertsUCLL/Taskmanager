package be.ucll.taskmanager.DTO;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Component
public class TaskDTO {
    //title not blank, not null and minimum length of 3
    @Size(min = 3)
    @NotBlank
    public String title;

    //description not blank, not null and minimum length of 8
    @Size(min = 8)
    @NotBlank
    public String description;

    //date and time when the task should be completed (in ISO standard)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime dueDateTime;


    ///
    /// setters and getters
    ///

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }


    public LocalDateTime getDueDateTime(){
        return this.dueDateTime;
    }

    public String getTitle() {return this.title;}


    public String getDescription() {return this.description;}

    public String toString() {return this.title + '\n';}
}
