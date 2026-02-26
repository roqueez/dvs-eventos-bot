package com.dvs.eventos.service;

import com.dvs.eventos.model.Event;
import com.dvs.eventos.model.Participant;

public class RatingService {

    public static void setRating(String userId, double rating) {
        Event event = EventService.getCurrentEvent();
        if (event == null) return;

        for (Participant p : event.getParticipants()) {
            if (p.getUserId().equals(userId)) {
                p.setRating(rating);
                break;
            }
        }
    }
}