package com.monteiro.healthcheckservice.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.monteiro.healthcheck.HealthCheckRequest;
import com.monteiro.healthcheck.HealthCheckResponse;

@Endpoint
public class HealthCheckEndpoint {

    private static final String NAMESPACE_URI = "http://monteiro.com/healthcheck";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "HealthCheckRequest")
    @ResponsePayload
    public HealthCheckResponse checkHealth(@RequestPayload HealthCheckRequest request) {
        HealthCheckResponse response = new HealthCheckResponse();
        response.setStatus("ok");
        return response;
    }
}
