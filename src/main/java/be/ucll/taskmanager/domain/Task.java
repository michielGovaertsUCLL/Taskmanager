package be.ucll.taskmanager.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Task {
    // sequential id
    private static final AtomicInteger count = new AtomicInteger(-1);
    private final int id;

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

    //empty constructor for spring
    public Task() {
        this.id = count.incrementAndGet();
    }



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

    public int getId() {return this.id;}

    public String getDescription() {return this.description;}

    public String toString() {return this.getId() + '\n' + this.title + '\n';}
}
