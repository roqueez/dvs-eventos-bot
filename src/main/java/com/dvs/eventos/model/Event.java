package com.dvs.eventos.model;

import java.util.ArrayList;
import java.util.List;

public class Event {

    private String messageId;
    private String channelId;
    private String horario;
    private String ip;

    private final List<Participant> participants = new ArrayList<>();
    private EventStatus status = EventStatus.ABERTO;

    // ===============================
    // Getters e Setters
    // ===============================

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }
}