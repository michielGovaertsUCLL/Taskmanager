package be.ucll.taskmanager.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "TASK")
public class Task{

    // sequential id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    public Long id;

    //obsolete
    //private Long subId = 0L;

    @Column(name = "TITLE")
    public String title;

    @Column(name = "DESCRIPTION")
    public String description;

    @Column(name = "DUEDATETIME")
    public LocalDateTime dueDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    public Task parent;

    @OneToMany(mappedBy = "parent")
    public List<Task> subtasks;

    //empty constructor initializes subtasks as arraylist
    public Task(){
        this.subtasks = new ArrayList<>();
    }

    //
    //subtask specific functions
    //

    //adds subtask
    public void addSubtask(Task t){
        this.subtasks.add(t);
    }

    //request subtask
    //TODO: this
    public Task getSubTask(Long id){
        for(Task t : this.getSubtasks()){
            if(t.getId()==id){
                return t;
            }
        }
        throw new NullPointerException("no task with this id");
    }

    //removes subtask
    public void removeSubTask(Long id){
        this.subtasks.remove(getSubTask(id));
    }

    //check if this task is a subtask
    public boolean hasSubtask() {
        return this.subtasks.size() != 0;
    }



    ///
    /// setters and getters
    ///

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        System.out.println("setID: " + id);
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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

    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }

    public List<Task> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Task> subtasks) {
        this.subtasks = subtasks;
    }


    //
    //overrides
    //

    @Override
    public String toString(){
        return this.id + '\n' + this.title + '\n' + this.description + '\n' + this.dueDateTime + '\n' + '\n';
    }



}
