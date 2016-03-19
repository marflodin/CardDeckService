package com.marflo.service.carddeck.db.entity;

import com.marflo.service.carddeck.entity.Card;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

@Entity
public class CardDeckEntity {

    @Id private String cardDeckId;
    private List<Card> drawnCards;

    public String getCardDeckId() {
        return cardDeckId;
    }

    public void setCardDeckId(String cardDeckId) {
        this.cardDeckId = cardDeckId;
    }

    public List<Card> getDrawnCards() {
        return drawnCards;
    }

    public void setDrawnCards(List<Card> drawnCards) {
        this.drawnCards = drawnCards;
    }
}
