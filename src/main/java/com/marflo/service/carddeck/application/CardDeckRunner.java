package com.marflo.service.carddeck.application;

import com.marflo.service.carddeck.configuration.CardDeckConfiguration;
import com.marflo.service.carddeck.endpoint.CardDeckService;
import com.marflo.service.carddeck.healthcheck.HealthCheckResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CardDeckRunner extends Application<CardDeckConfiguration> {

    public static void main(String[] args) throws Exception {
        //new CardDeckRunner().run(args);
        new CardDeckRunner().run("server");
    }

    @Override
    public void initialize(Bootstrap<CardDeckConfiguration> bootstrap) {
    }

    @Override
    public void run(CardDeckConfiguration configuration,
                    Environment environment) {
        final CardDeckService endpoint = new CardDeckService();
        final HealthCheckResource healthCheck = new HealthCheckResource();

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(endpoint);
    }
}
