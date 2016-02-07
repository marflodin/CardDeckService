package com.marflo.service.carddeck.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marflo.service.carddeck.entity.Card;

import java.util.List;

public class CardDeckResponse {

    private String deckId;
    private List<Card> cards;

    public CardDeckResponse() {
    }

    public CardDeckResponse(String deckId, List<Card> cards) {
        this.deckId = deckId;
        this.cards = cards;
    }

    @JsonProperty
    public String getDeckId() {
        return deckId;
    }

    @JsonProperty
    public List<Card> getCards() {
        return cards;
    }
}
