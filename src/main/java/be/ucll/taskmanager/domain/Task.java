package be.ucll.taskmanager.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class Task{
    // sequential id
    private int id;
    private int subId = 0;
    public List<Task> subtasks;


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

    public Task(){
        this.subtasks = new ArrayList<>();
    }

    public void addSubtask(Task t){
        subId++;
        t.setId(subId);
        this.subtasks.add(t);
    }

    public Task getSubTask(int id){
        for(Task t : this.subtasks){
            if(t.getId()==id){
                return t;
            }
        }
        throw new NullPointerException("no task with this id");
    }

    public void removeSubTask(int id){
        this.subtasks.remove(getSubTask(id));
    }



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

    public List<Task> getSubtasks(){
        return this.subtasks;
    }

    public LocalDateTime getDueDateTime(){
        return this.dueDateTime;
    }

    public String getTitle() {return this.title;}


    public String getDescription() {return this.description;}

    public int getId() {return this.id;}

    public void setId(int id) {this.id=id;}

    public String toString(){
        return this.id + '\n' + this.title + '\n' + this.description + '\n' + this.dueDateTime + '\n' + '\n';
    }
}
