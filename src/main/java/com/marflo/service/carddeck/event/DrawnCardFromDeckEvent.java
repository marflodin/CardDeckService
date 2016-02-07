package com.marflo.service.carddeck.event;

import com.marflo.service.carddeck.entity.Card;

public class DrawnCardFromDeckEvent {

    private String deckId;
    private Card drawnCard;

    public DrawnCardFromDeckEvent(String deckId, Card drawnCard) {
        this.deckId = deckId;
        this.drawnCard = drawnCard;
    }
}
