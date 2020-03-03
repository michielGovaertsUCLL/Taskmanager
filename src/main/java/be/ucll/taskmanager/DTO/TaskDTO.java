package be.ucll.taskmanager.DTO;

import java.time.LocalDateTime;

public class TaskDTO {
    public String title;
    public String description;
    public String dueDate;

    public TaskDTO(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
}
