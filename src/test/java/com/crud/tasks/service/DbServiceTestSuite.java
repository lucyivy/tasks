package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void testGetAllTasks() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Title", "Content"));
        when(repository.findAll()).thenReturn(taskList);

        //When
        List<Task> resultList = dbService.getAllTasks();

        //Then
        Assert.assertEquals(1, resultList.size());
    }

    @Test
    public void testGetTask() {
        //Given
        Task task = new Task(1L, "Title", "Content");
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        //When
        Task result = dbService.getTask(1L).get();

        //Then
        Assert.assertEquals(1L, result.getId(), 0.1);
        Assert.assertEquals("Title", result.getTitle());
        Assert.assertEquals("Content", result.getContent());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "Title", "Content");
        Task updatedTask = new Task(1L, "Title2", "Content2");
        when(repository.save(task)).thenReturn(updatedTask);

        //When
        Task result = dbService.saveTask(task);

        //Then
        Assert.assertEquals(1L, result.getId(), 0.1);
        Assert.assertEquals("Title2", result.getTitle());
        Assert.assertEquals("Content2", result.getContent());
    }

    @Test
    public void testDeleteTask() {
        //Given
        Task task = new Task(1L, "Title", "Content");

        //When
        dbService.deleteTask(1L);

        //Then
        Mockito.verify(repository, times(1)).deleteById(1L);

    }
}
