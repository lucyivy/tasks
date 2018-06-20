package com.crud.tasks.trello.config;

import org.junit.Assert;
import org.junit.Test;


public class TrelloConfigTestSuite {


    private TrelloConfig config = new TrelloConfig("https://api.trello.com/1",
            "fakeAppKey",
            "fakeTrelloToken",
            "fakeUserName");

    @Test
    public void testGetTrelloAppKey() {
        //When
        String result = config.getTrelloApiEndpoint();

        //Then
        Assert.assertEquals("https://api.trello.com/1", result);
    }

    @Test
    public void testGetTrelloApiEndpoint() {
        //When
        String result = config.getTrelloAppKey();

        //Then
        Assert.assertEquals("fakeAppKey", result);
    }

    @Test
    public void testGetTrelloToken() {
        //When
        String result = config.getTrelloToken();

        //Then
        Assert.assertEquals("fakeTrelloToken", result);
    }

    @Test
    public void testGetTrelloUserName() {
        //When
        String result = config.getTrelloUsername();

        //Then
        Assert.assertEquals("fakeUserName", result);
    }
}
