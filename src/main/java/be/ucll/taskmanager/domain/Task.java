package be.ucll.taskmanager.domain;

import be.ucll.taskmanager.DTO.TaskDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Task extends TaskDTO {
    // sequential id
    private static final AtomicInteger count = new AtomicInteger(-1);
    private final int id;


    //empty constructor for spring
    public Task() {
        this.id = count.incrementAndGet();
    }

    public int getId() {return this.id;}

    public String toString(){
        return this.getId() + '\n' + super.toString();
    }
}
