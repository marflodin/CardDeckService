package com.marflo.service.carddeck.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CreateCardDeckEvent {

    @JsonProperty
    private Date eventTimestamp;
    @JsonProperty
    private String handId;
    @JsonProperty
    private String deckId;

    public CreateCardDeckEvent(String handId, String deckId) {
        this.eventTimestamp = new Date();
        this.handId = handId;
        this.deckId = deckId;
    }
}
