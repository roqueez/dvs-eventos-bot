package com.dvs.eventos.model;

public class Participant {

    private final String userId;
    private final String username;
    private Double rating;

    public Participant(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public String getUserId() { return userId; }
    public String getUsername() { return username; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
}