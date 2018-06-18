package com.crud.tasks.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.crud.tasks.domain.*;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.domain.TrelloCardDto;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper mapper;

    @Test
    public void testToBoardMapperAndToList() {
        //Given
        TrelloListDto list1 = new TrelloListDto("1", "test", true);
        List<TrelloListDto> listList = new ArrayList<>();
        List<TrelloListDto> listList2 = new ArrayList<>();
        listList.add(list1);
        listList2.add(list1);
        listList2.add(list1);
        TrelloBoardDto boardDto1 = new TrelloBoardDto("1", "test", listList);
        TrelloBoardDto boardDto2 = new TrelloBoardDto("2", "test2", listList2);
        List<TrelloBoardDto> boardDtoList = new ArrayList<>();
        boardDtoList.add(boardDto1);
        boardDtoList.add(boardDto2);

        //When
        List<TrelloBoard> boardList = mapper.mapToBoards(boardDtoList);


        //Then
        Assert.assertEquals("1", boardList.get(0).getId());
        Assert.assertEquals("2", boardList.get(1).getId());
        Assert.assertEquals("test", boardList.get(0).getName());
        Assert.assertEquals("test2", boardList.get(1).getName());
        Assert.assertEquals(1, boardList.get(0).getLists().size());
        Assert.assertEquals(2, boardList.get(1).getLists().size());
    }

    @Test
    public void testToBoardDtoMapperAndToListDto() {
        //Given
        TrelloList list1 = new TrelloList("1", "test", true);
        List<TrelloList> listList = new ArrayList<TrelloList>();
        List<TrelloList> listList2 = new ArrayList<TrelloList>();
        listList.add(list1);
        listList2.add(list1);
        listList2.add(list1);
        TrelloBoard board1 = new TrelloBoard("1", "test", listList);
        TrelloBoard board2 = new TrelloBoard("2", "test2", listList2);
        List<TrelloBoard> boardList = new ArrayList<TrelloBoard>();
        boardList.add(board1);
        boardList.add(board2);

        //When
        List<TrelloBoardDto> boardDtoList = mapper.mapToBoardDto(boardList);

        //Then
        Assert.assertEquals("1", boardDtoList.get(0).getId());
        Assert.assertEquals("2", boardDtoList.get(1).getId());
        Assert.assertEquals("test", boardDtoList.get(0).getName());
        Assert.assertEquals("test2", boardDtoList.get(1).getName());
        Assert.assertEquals(1, boardDtoList.get(0).getLists().size());
        Assert.assertEquals(2, boardDtoList.get(1).getLists().size());
    }

    @Test
    public void testMapToTrelloCardDto() {
        //Given
        TrelloCard card = new TrelloCard("name1", "description1", "pos1", "listId1");

        //When
        TrelloCardDto cardDto = mapper.mapToTrelloCardDto(card);

        //Then
        Assert.assertEquals(card.getName(), cardDto.getName());
        Assert.assertEquals(card.getDescription(), cardDto.getDescription());
        Assert.assertEquals(card.getPos(), cardDto.getPos());
        Assert.assertEquals(card.getListId(), cardDto.getListId());
    }

    @Test
    public void testMapToTrelloCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("name1", "description1", "pos1", "listId1");

        //When
        TrelloCard card = mapper.mapToTrelloCard(cardDto);

        //Then
        Assert.assertEquals(cardDto.getName(), card.getName());
        Assert.assertEquals(cardDto.getDescription(), card.getDescription());
        Assert.assertEquals(cardDto.getPos(), card.getPos());
        Assert.assertEquals(cardDto.getListId(), card.getListId());
    }
}