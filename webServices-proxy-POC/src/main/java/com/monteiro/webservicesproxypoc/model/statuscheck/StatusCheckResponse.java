package com.monteiro.webservicesproxypoc.model.statuscheck;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "statusCheckResponse", namespace = "http://www.monteiro.com/webservicesproxypoc")
public class StatusCheckResponse {

    private String status;
    private String date;
    private String time;

    public StatusCheckResponse() {
    }

    public StatusCheckResponse(String status, String date, String time) {
        this.status = status;
        this.date = date;
        this.time = time;
    }

    @XmlElement
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @XmlElement
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
