package com.marflo.service.carddeck.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marflo.service.carddeck.entity.Card;

import java.util.Date;
import java.util.List;

public class DrawCardsFromDeckEvent {

    @JsonProperty
    private Date eventTimestamp;
    @JsonProperty
    private String handId;
    @JsonProperty
    private String deckId;
    @JsonProperty
    private List<Card> cards;

    public DrawCardsFromDeckEvent(String handId, String deckId, List<Card> cards) {
        this.eventTimestamp = new Date();
        this.handId = handId;
        this.deckId = deckId;
        this.cards = cards;
    }
}
