package com.dvs.eventos.service;

import com.dvs.eventos.model.Event;
import com.dvs.eventos.model.EventStatus;
import com.dvs.eventos.model.Participant;

public class SelectionService {

    private static final int MAX_PARTICIPANTS = 30;

    public static boolean tryAddParticipant(String userId, String username) {

        Event event = EventService.getCurrentEvent();
        if (event == null) return false;
        if (event.getStatus() == EventStatus.FECHADO) return false;

        if (event.getParticipants().size() >= MAX_PARTICIPANTS) {
            event.setStatus(EventStatus.FECHADO);
            return false;
        }

        boolean alreadyIn = event.getParticipants()
                .stream()
                .anyMatch(p -> p.getUserId().equals(userId));

        if (alreadyIn) return false;

        event.getParticipants().add(new Participant(userId, username));

        if (event.getParticipants().size() >= MAX_PARTICIPANTS) {
            event.setStatus(EventStatus.FECHADO);
            return true;
        }

        return false;
    }

    public static void removeParticipant(String userId) {

        Event event = EventService.getCurrentEvent();
        if (event == null) return;

        event.getParticipants().removeIf(p -> p.getUserId().equals(userId));

        if (event.getStatus() == EventStatus.FECHADO &&
                event.getParticipants().size() < MAX_PARTICIPANTS) {

            event.setStatus(EventStatus.ABERTO);
        }
    }
}