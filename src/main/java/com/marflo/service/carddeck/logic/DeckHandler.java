package com.marflo.service.carddeck.logic;

import com.marflo.service.carddeck.entity.Card;

public interface DeckHandler {
    String createDeck();
    Card drawCardForDeck(String deckId);
}
