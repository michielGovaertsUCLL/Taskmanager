package be.ucll.taskmanager.repository;

import be.ucll.taskmanager.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TaskRepo extends JpaRepository<Task, Long> {

    /*
    #create table task query
    CREATE TABLE task (
        id IDENTITY NOT NULL PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        description VARCHAR(255),
        due_date_time TIMESTAMP,
        parent_id LONG NULL
    );
    ALTER TABLE task
    ADD CONSTRAINT FK_TASK_ID FOREIGN KEY(parent_id)
    REFERENCES task(id);

    INSERT INTO TASK VALUES (0, 'task1', 'desc', now(), null);
    INSERT INTO TASK VALUES (1, 'subtask1', 'desc1', now(), 0);
    INSERT INTO TASK VALUES (2, 'subtask2', 'desc2', now(), 0);
     */

    @Query(value = "SELECT * FROM TASK WHERE PARENT_ID IS NULL", nativeQuery = true)
    List<Task> getMainTasks();

    @Query(value = "SELECT * FROM TASK WHERE id = ?1 group by due_date_time;", nativeQuery = true)
    void getSubtasks(Long parentId);

    @Query(value = "DELETE TASK WHERE id =?1", nativeQuery = true)
    void deleteSubtask(Long parentId);

}
