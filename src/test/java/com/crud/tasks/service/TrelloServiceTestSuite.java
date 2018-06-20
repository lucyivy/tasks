package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
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
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService service;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService mailService;

    @Mock
    private AdminConfig adminConfig;


    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        TrelloBoardDto board = new TrelloBoardDto("1", "test", trelloLists);
        trelloBoards.add(board);

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);

        //when
        List<TrelloBoardDto> result = service.fetchTrelloBoards();

        //Then
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(board.getName(), result.get(0).getName());
    }

    @Test
    public void testCreateCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("test", "test_description", "testPos", "testcostam");
        CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("1", "test", "testPos");

        when(trelloClient.createNewCard(cardDto)).thenReturn(createdCard);

        //When
        CreatedTrelloCardDto resultCard = service.createdTrelloCard(cardDto);

        //Then
        Assert.assertEquals(cardDto.getName(), resultCard.getName());
    }
}
