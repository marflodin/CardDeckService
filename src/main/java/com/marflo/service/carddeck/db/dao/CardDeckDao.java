package com.marflo.service.carddeck.db.dao;

import com.marflo.service.carddeck.db.entity.CardDeckEntity;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

public class CardDeckDao extends BasicDAO<CardDeckEntity, String> {

    public CardDeckDao(MongoClient mongoClient, Morphia morphia) {
        super(mongoClient, morphia, "dbName");
    }
}
