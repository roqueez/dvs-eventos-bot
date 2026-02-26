package com.dvs.eventos.model;

import java.util.ArrayList;
import java.util.List;

public class Event {

    private String messageId;
    private final List<Participant> participants = new ArrayList<>();
    private EventStatus status = EventStatus.ABERTO;

    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }

    public List<Participant> getParticipants() { return participants; }

    public EventStatus getStatus() { return status; }
    public void setStatus(EventStatus status) { this.status = status; }
}