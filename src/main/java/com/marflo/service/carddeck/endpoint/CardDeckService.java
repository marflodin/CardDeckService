package com.marflo.service.carddeck.endpoint;

import com.codahale.metrics.annotation.Timed;
import com.marflo.service.carddeck.api.CardDeckResponse;
import com.marflo.service.carddeck.entity.Card;
import com.marflo.service.carddeck.event.KafkaProducer;
import com.marflo.service.carddeck.logic.DeckHandler;
import com.marflo.service.carddeck.logic.DeckHandlerImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/card-deck")
@Produces(MediaType.APPLICATION_JSON)
public class CardDeckService {

    private DeckHandler deckHandler = new DeckHandlerImpl();
    private KafkaProducer kafkaProducer = new KafkaProducer();

    @PUT
    @Timed
    public String createDeck() {
        String deckId = deckHandler.createDeck();
        kafkaProducer.sendMessageToTopic("createdCardDeck", deckId);
        return deckId;
    }

    @GET
    @Path("{deckId}/{numberOfCards}")
    @Timed
    public CardDeckResponse getCardsFromDeck(@PathParam("deckId") String deckId,
                                             @PathParam("numberOfCards") Integer numberOfCards) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            cards.add(deckHandler.drawCardForDeck(deckId));
        }
        CardDeckResponse cardDeckResponse = new CardDeckResponse(deckId, cards);
        kafkaProducer.sendMessageToTopic("drawnCardFromDeck", cardDeckResponse);
        return cardDeckResponse;
    }
}
