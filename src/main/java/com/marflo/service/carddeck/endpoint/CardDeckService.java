package com.marflo.service.carddeck.endpoint;

import com.codahale.metrics.annotation.Timed;
import com.marflo.service.carddeck.api.CardDeckResponse;
import com.marflo.service.carddeck.entity.Card;
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

    @PUT
    @Timed
    public String createDeck() {
        return deckHandler.createDeck();
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
        return new CardDeckResponse(deckId, cards);
    }
}
