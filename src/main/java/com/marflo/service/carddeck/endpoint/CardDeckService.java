package com.marflo.service.carddeck.endpoint;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marflo.service.carddeck.api.CardDeckResponse;
import com.marflo.service.carddeck.entity.Card;
import com.marflo.service.carddeck.event.CreateCardDeckEvent;
import com.marflo.service.carddeck.event.DrawCardsFromDeckEvent;
import com.marflo.service.carddeck.event.KafkaProducer;
import com.marflo.service.carddeck.logic.DeckHandler;
import com.marflo.service.carddeck.logic.DeckHandlerImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/card-deck/{handId}")
@Produces(MediaType.APPLICATION_JSON)
public class CardDeckService {

    private DeckHandler deckHandler = new DeckHandlerImpl();
    private KafkaProducer kafkaProducer = new KafkaProducer();
    ObjectMapper mapper = new ObjectMapper();

    @PUT
    @Timed
    public Response createDeck(@PathParam("handId") String handId) throws JsonProcessingException {

        String deckId = deckHandler.createDeck();
        CreateCardDeckEvent createCardDeckEvent = new CreateCardDeckEvent(handId, deckId);
        kafkaProducer.sendMessageToTopic("createdCardDeck",
                mapper.writeValueAsString(createCardDeckEvent));

        return Response.ok()
                .entity(deckId)
                .build();
    }

    @GET
    @Path("{deckId}/{numberOfCards}")
    @Timed
    public Response getCardsFromDeck(@PathParam("handId") String handId,
                                     @PathParam("deckId") String deckId,
                                     @PathParam("numberOfCards") Integer numberOfCards) throws JsonProcessingException {

        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            cards.add(deckHandler.drawCardForDeck(deckId));
        }
        CardDeckResponse cardDeckResponse = new CardDeckResponse(deckId, cards);
        DrawCardsFromDeckEvent drawCardsFromDeckEvent = new DrawCardsFromDeckEvent(handId,
                deckId, cards);
        kafkaProducer.sendMessageToTopic("drawCardsFromDeck",
                mapper.writeValueAsString(drawCardsFromDeckEvent));

        return Response.ok()
                .entity(cardDeckResponse)
                .build();
    }
}
