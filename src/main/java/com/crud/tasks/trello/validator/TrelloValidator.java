package com.crud.tasks.trello.validator;

import org.springframework.stereotype.Component;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TrelloValidator {

    private final static Logger LOGGER = LoggerFactory.getLogger(TrelloValidator.class);

    public void validateCard(final TrelloCard trelloCard) {
        if(trelloCard.getName().contains("test")) {
            LOGGER.info("Someone is testing");
        } else {
            LOGGER.info("Application is used in proper way");
        }
    }

    public List<TrelloBoard> validateTrelloBoards(List<TrelloBoard> boardList) {
        LOGGER.info("Starting to filter boards...");
        List<TrelloBoard> filteredBoards = boardList.stream()
                .filter(board -> !board.getName().equalsIgnoreCase("test"))
                .collect(Collectors.toList());
        LOGGER.info("Boards have been filtered. Current list size: " + filteredBoards.size());
        return filteredBoards;
    }
}