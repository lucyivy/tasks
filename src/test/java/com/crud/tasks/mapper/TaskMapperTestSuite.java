package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    private TaskMapper mapper;

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task((long) 1, "test name", "test description");

        //When
        TaskDto result = mapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals((Long) (long) 1, result.getId());
        Assert.assertEquals("test name",  result.getTitle());
        Assert.assertEquals("test description", result.getContent());
    }

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto =  new TaskDto((long) 1, "test name", "test description");

        //When
        Task result = mapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals((Long) (long) 1, result.getId());
        Assert.assertEquals("test name",  result.getTitle());
        Assert.assertEquals("test description", result.getContent());
    }

}
