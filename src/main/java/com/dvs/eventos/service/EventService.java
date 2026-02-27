package com.dvs.eventos.service;

import com.dvs.eventos.model.Event;
import com.dvs.eventos.repository.EventRepository;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class EventService {

    private static final String LOG_CHANNEL_ID = "1197973500657401967";

    public static void createEvent(String messageId, String channelId, String horario, String ip) {

        Event event = new Event();
        event.setMessageId(messageId);
        event.setChannelId(channelId);
        event.setHorario(horario);
        event.setIp(ip);

        EventRepository.setEvent(event);
    }

    public static Event getCurrentEvent() {
        return EventRepository.getEvent();
    }

    public static void sendLog(JDA jda, String message) {

        TextChannel logChannel = jda.getTextChannelById(LOG_CHANNEL_ID);
        if (logChannel == null) return;

        logChannel.sendMessage(message).queue();
    }

    // 🔥 NOVO: envia lista completa no canal de logs
    public static void updateParticipantsLog(JDA jda) {

        Event event = getCurrentEvent();
        if (event == null) return;

        TextChannel logChannel = jda.getTextChannelById(LOG_CHANNEL_ID);
        if (logChannel == null) return;

        StringBuilder texto = new StringBuilder();

        texto.append("📋 **Participantes (")
                .append(event.getParticipants().size())
                .append("/30)**\n\n");

        event.getParticipants().forEach(p ->
                texto.append("• ").append(p.getUsername()).append("\n")
        );

        logChannel.sendMessage(texto.toString()).queue();
    }
}