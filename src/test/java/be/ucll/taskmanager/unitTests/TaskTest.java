package be.ucll.taskmanager.unitTests;


import be.ucll.taskmanager.dto.TaskDTO;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class TaskTest {

    // title tests

    @Test
    public void testSetTitleThrowsExceptionOnNull(){
        TaskDTO taskDTO = new TaskDTO();

        assertThrows(NullPointerException.class, () -> {taskDTO.setTitle(null);});
    }

    @Test
    public void testSetTitleThrowsExceptionOnBlank(){
        TaskDTO taskDTO = new TaskDTO();

        assertThrows(IllegalArgumentException.class, () -> {taskDTO.setTitle("          ");});
    }

    @Test
    public void testSetTitleThrowsExceptionOnNotEnoughCharacters(){
        TaskDTO taskDTO = new TaskDTO();

        assertThrows(IllegalArgumentException.class, () -> {taskDTO.setTitle("xx");});
    }

    @Test
    public void testSetTitleThrowsExceptionOnTooMuchCharacters(){
        TaskDTO taskDTO = new TaskDTO();

        String longString = new String(new char[36]).replace("\0", "q"); //this generates a string of 36 characters(letter q)

        assertThrows(IllegalArgumentException.class, () -> {taskDTO.setTitle(longString);});
    }


    //description test

    //TODO


    //date & time tests

    //TODO


    //subtask tests

    //TODO
}
