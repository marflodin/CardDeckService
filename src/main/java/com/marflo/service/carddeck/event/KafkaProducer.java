package com.marflo.service.carddeck.event;

import java.util.*;

import com.marflo.service.carddeck.api.CardDeckResponse;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer {

    Producer<String, String> producer;
    Producer<String, CardDeckResponse> cardDeckResponseProducer;

    public KafkaProducer() {
        Properties props = new Properties();
        props.put("metadata.broker.list", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "com.marflo.service.carddeck.event.SimplePartitioner");
        props.put("request.required.acks", "1");

        ProducerConfig config = new ProducerConfig(props);

        producer = new Producer<String, String>(config);
        cardDeckResponseProducer = new Producer<String, CardDeckResponse>(config);
    }

    public void sendMessageToTopic(String topic, String message) {
        KeyedMessage<String, String> data =
                new KeyedMessage<String, String>(topic, message);
        producer.send(data);
    }
}
