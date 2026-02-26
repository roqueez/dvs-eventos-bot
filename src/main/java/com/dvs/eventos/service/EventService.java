package com.dvs.eventos.service;

import com.dvs.eventos.model.Event;
import com.dvs.eventos.repository.EventRepository;

public class EventService {

    public static void createEvent(String messageId) {
        Event event = new Event();
        event.setMessageId(messageId);
        EventRepository.setEvent(event);
    }

    public static Event getCurrentEvent() {
        return EventRepository.getEvent();
    }
}