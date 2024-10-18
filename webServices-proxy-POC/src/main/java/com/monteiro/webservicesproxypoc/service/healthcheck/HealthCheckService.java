package com.monteiro.webservicesproxypoc.service.healthcheck;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.monteiro.webservicesproxypoc.model.healthcheck.HealthCheckRequest;
import com.monteiro.webservicesproxypoc.model.healthcheck.HealthCheckResponse;

@Endpoint
public class HealthCheckService {

    private static final String NAMESPACE_URI = "http://www.monteiro.com/webservicesproxypoc";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "healthCheckRequest")
    @ResponsePayload
    public HealthCheckResponse checkHealth(@RequestPayload HealthCheckRequest request) {
        return new HealthCheckResponse("Ok");
    }
}
