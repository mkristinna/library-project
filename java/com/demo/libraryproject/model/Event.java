package com.demo.libraryproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "event")
public class Event {
    @Id
    private String id;

    private String naziv;

    private LocalDateTime time;

    private String loggedUser;

    private String kategorija;

    private String detalji;

    private String status;

    public Event(String naziv, LocalDateTime time, String loggedUser, String kategorija, String detalji, String status) {
        this.naziv = naziv;
        this.time = time;
        this.loggedUser = loggedUser;
        this.kategorija = kategorija;
        this.detalji = detalji;
        this.status = status;
    }

    public Event() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getDetalji() {
        return detalji;
    }

    public void setDetalji(String detalji) {
        this.detalji = detalji;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }
}
