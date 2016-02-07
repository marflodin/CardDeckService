package com.marflo.service.carddeck.logic;

import com.marflo.service.carddeck.entity.Card;
import com.marflo.service.carddeck.entity.Rank;
import com.marflo.service.carddeck.entity.Suit;

import java.util.*;

public class DeckHandlerImpl implements DeckHandler {

    private Map<String, List<Card>> cards = new HashMap<>();

    public String createDeck() {
        String uniqueID = UUID.randomUUID().toString();
        cards.put(uniqueID, fillDeckWithCards());
        return uniqueID;
    }

    private List<Card> fillDeckWithCards() {
        List<Card> cardsInDeck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cardsInDeck.add(new Card(suit, rank));
            }
        }
        return cardsInDeck;
    }

    public Card drawCardForDeck(String deckId) {
        List<Card> cardsInDeck = cards.get(deckId);
        if (cardsInDeck.isEmpty())
            throw new IllegalArgumentException("deck is empty");
        int randomCardIndexInDeck = new Random().nextInt(cardsInDeck.size());
        Card randomCardFromDeck = cardsInDeck.get(randomCardIndexInDeck);
        cardsInDeck.remove(randomCardFromDeck);
        return randomCardFromDeck;
    }
}
