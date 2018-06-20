package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloControllerTestSuite {

    @InjectMocks
    private TrelloController controller;

    @Mock
    private TrelloFacade facade;

    @Test
    public void testGetTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto());
        trelloBoardDtos.add(new TrelloBoardDto());
        when(facade.fetchTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> resultDtoList = controller.getTrelloBoards();

        //Then
        Assert.assertEquals(2, resultDtoList.size());
    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("TestName", "Description", "bottom", "1");
        when(facade.createCard(trelloCardDto)).thenReturn(new CreatedTrelloCardDto("1", "TestName", "testurl"));

        //When
        CreatedTrelloCardDto resultCardDto = controller.createTrelloCard(trelloCardDto);

        //Then
        Assert.assertEquals("TestName", resultCardDto.getName());
    }
}
