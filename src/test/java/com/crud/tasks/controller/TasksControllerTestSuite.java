package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
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
public class TasksControllerTestSuite {

    @InjectMocks
    private TaskController controller;

    @Mock
    private DbService service;

    @Mock
    private TaskMapper mapper;


    @Test
    public void testCreateTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");

        //When
        controller.createTask(taskDto);

        //Then
        Mockito.verify(service, times(1)).saveTask(Mockito.any());
    }

    @Test
    public void testGetTasks() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "Title", "Content");
        taskDtoList.add(taskDto);
        taskList.add(task);
        when(service.getAllTasks()).thenReturn(taskList);
        when(mapper.mapToTaskDtoList(Mockito.anyList())).thenReturn(taskDtoList);

        //When
        List<TaskDto> resultList = controller.getTasks();

        //Then
        Assert.assertEquals(1, resultList.size());
        Assert.assertEquals("Title", resultList.get(0).getTitle());
    }

    @Test
    public void testGetTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Task task = new Task(1L, "Title", "Content");
        when(mapper.mapToTaskDto(Mockito.any())).thenReturn(taskDto);
        when(service.getTask(1L)).thenReturn(Optional.of(task));

        //When
        TaskDto resultDto = new TaskDto();
        try {
            resultDto = controller.getTask(1L);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        //Then
        Assert.assertEquals(resultDto.getId(), 1L, 0.1);
        Assert.assertEquals("Title", resultDto.getTitle());
        Assert.assertEquals("Content", resultDto.getContent());
    }

    @Test
    public void testUpdateTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Task task = new Task(1L, "Title", "Content");
        when(mapper.mapToTaskDto(Mockito.any())).thenReturn(taskDto);

        //When
        TaskDto resultDto = controller.updateTask(taskDto);

        //Then
        Assert.assertEquals(resultDto.getId(), 1L, 0.1);
        Assert.assertEquals("Title", resultDto.getTitle());
        Assert.assertEquals("Content", resultDto.getContent());
    }

    @Test
    public void testDeleteTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");

        //When
        controller.deleteTask(1L);

        //Then
        Mockito.verify(service, times(1)).deleteTask(Mockito.any());
    }
}
