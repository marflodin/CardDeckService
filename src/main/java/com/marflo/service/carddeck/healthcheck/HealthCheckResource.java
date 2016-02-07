package com.marflo.service.carddeck.healthcheck;

import com.codahale.metrics.health.HealthCheck;

public class HealthCheckResource extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        //TODO: add api call
        return Result.healthy();
    }
}
