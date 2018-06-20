package com.crud.tasks.domain;

import org.junit.Test;

import org.junit.Assert;

import static org.mockito.Mockito.when;


public class DtoTestSuite {
    @Test
    public void testTaskAndTaskDto() {

        //When
        Task task = new Task((long) 1, "test name", "test description");
        TaskDto taskDto =  new TaskDto((long) 1, "test name", "test description");

        //Then
        Assert.assertEquals(task.getTitle(), taskDto.getTitle());
        Assert.assertEquals(task.getId(), taskDto.getId());
        Assert.assertEquals(task.getContent(), taskDto.getContent());
    }

    @Test
    public void testMail() {

        //Given & When
        Mail mail = new Mail("testMailTo", "TestSubject", "TestMessage");

        //Then
        Assert.assertNull(mail.getToCc());
        Assert.assertEquals("testMailTo",  mail.getMailTo());
        Assert.assertEquals("TestSubject",  mail.getSubject());
        Assert.assertEquals("TestMessage", mail.getMessage());
    }

    @Test
    public void createdTrelloCardDtoTest(){
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "test", "testURL");
        TrelloDto trelloDto = new TrelloDto(1,2);
        AttachmentByTypeDto attachmentByTypeDto = new AttachmentByTypeDto(trelloDto);

        //when
        BadgesDto badges = new BadgesDto(1, attachmentByTypeDto);

        //then
        Assert.assertEquals(1, badges.getAttachmentsByType().getTrelloDto().getBoard());
        Assert.assertEquals(2, badges.getAttachmentsByType().getTrelloDto().getCard());
        Assert.assertEquals(1, badges.getVotes());
        Assert.assertEquals("1", createdTrelloCardDto.getId());
        Assert.assertEquals("test", createdTrelloCardDto.getName());
        Assert.assertEquals("testURL", createdTrelloCardDto.getShortUrl());
    }
}
