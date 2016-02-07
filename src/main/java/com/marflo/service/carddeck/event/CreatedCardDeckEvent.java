package com.marflo.service.carddeck.event;

public class CreatedCardDeckEvent {

    private String deckId;

    public CreatedCardDeckEvent(String deckId) {
        this.deckId = deckId;
    }
}
