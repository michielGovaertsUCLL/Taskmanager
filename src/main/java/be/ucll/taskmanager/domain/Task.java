package be.ucll.taskmanager.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;


public class Task {
    private static final AtomicInteger count = new AtomicInteger(-1);
    private final int id;
    private String title;
    private String description;
    private LocalDateTime dueDateTime;

    public Task(String title, String description, int year, int month, int day, int hour) {
        setTitle(title);
        setDueDateTime(year, month, day, hour, 0);
        this.id = count.incrementAndGet();
        setDescription(description);
    }

    private void setDescription(String description) {
        if(description.isBlank() || description == null || description.isEmpty()) throw new IllegalArgumentException();
        this.description = description;
    }

    private void setTitle(String title) {
        if(title.isBlank() || title == null || title.isEmpty()) throw new IllegalArgumentException();
        else this.title = title;
    }

    private void setDueDateTime(int year, int month, int day, int hour, int minute) {
        if(isValidDate(year, month, day) && isValidTime(hour, minute)) this.dueDateTime = LocalDateTime.of(year, month, day, hour, minute);
        else throw new IllegalArgumentException("invalid date or time");
    }

    private boolean isValidTime(int hour, int minute) {
        return hour < 24 && minute < 60;
    }

    private boolean isValidDate(int year, int month, int day) {
        return year > 2000 && month < 13 && day < 32;
    }


    public String getDueDateTime(){
        //TODO maak mooi
        String hour = this.dueDateTime.getHour() > 12 ? this.dueDateTime.getHour()-12 + "pm" : this.dueDateTime.getHour() + "am";
        return "due " + this.dueDateTime.format(DateTimeFormatter.ofPattern("MMMM dd yyyy")).toString() + " at "   + hour;
    }

    public String getTitle() {return this.title;}

    public int getId() {return this.id;}

    public String getDescription() {return this.description;}

    public String toString() {return this.getId() + '\n' + this.title + '\n';}
}
