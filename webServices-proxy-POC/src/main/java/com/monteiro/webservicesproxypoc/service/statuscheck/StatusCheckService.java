package com.monteiro.webservicesproxypoc.service.statuscheck;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.monteiro.webservicesproxypoc.model.statuscheck.StatusCheckRequest;
import com.monteiro.webservicesproxypoc.model.statuscheck.StatusCheckResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Endpoint
public class StatusCheckService {

    private static final String NAMESPACE_URI = "http://www.monteiro.com/webservicesproxypoc";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "statusCheckRequest")
    @ResponsePayload
    public StatusCheckResponse getStatus(@RequestPayload StatusCheckRequest request) {
        String status = "Ok";
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return new StatusCheckResponse(status, date, time);
    }
}
