package com.dvs.eventos.repository;

import com.dvs.eventos.model.Event;

public class EventRepository {

    private static Event currentEvent;

    public static void setEvent(Event event) {
        currentEvent = event;
    }

    public static Event getEvent() {
        return currentEvent;
    }

    public static void clear() {
        currentEvent = null;
    }
}