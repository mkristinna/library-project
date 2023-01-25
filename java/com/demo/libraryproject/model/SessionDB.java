package com.demo.libraryproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "session")
public class SessionDB {

    @Id
    private String id;

    private String username;

    private String token;

    private LocalDateTime time;

    public SessionDB(String username, String token, LocalDateTime time) {
        this.username = username;
        this.token = token;
        this.time = time;
    }

    public SessionDB() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
