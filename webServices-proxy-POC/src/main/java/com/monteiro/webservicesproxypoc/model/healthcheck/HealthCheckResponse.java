package com.monteiro.webservicesproxypoc.model.healthcheck;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "healthCheckResponse", namespace = "http://www.monteiro.com/webservicesproxypoc")
public class HealthCheckResponse {

    private String status;

    public HealthCheckResponse() {
    }

    public HealthCheckResponse(String status) {
        this.status = status;
    }

    @XmlElement
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
