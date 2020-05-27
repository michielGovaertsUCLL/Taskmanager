package be.ucll.taskmanager.unitTests;

import be.ucll.taskmanager.domain.Task;
import be.ucll.taskmanager.dto.TaskDTO;
import be.ucll.taskmanager.repository.TaskRepo;
import be.ucll.taskmanager.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class TaskServiceTest {

    private TaskService service = new TaskService();
    @Autowired
    private TaskRepo taskRepo;

    @Test
    public void testGetTasks(){
        //setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1L);
        taskDTO.setTitle("task 1");
        taskDTO.setDescription("this is a vague description");
        taskDTO.setDueDateTime(LocalDateTime.now());

        service.addTask(taskDTO);

        //testing method
        List<TaskDTO> tasks = service.getTasks();

        //tests
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());
        Assertions.assertEquals(1, tasks.size());
        Assertions.assertNotNull(tasks.get(0));
        Assertions.assertEquals(tasks.get(0), taskDTO);
    }
}
