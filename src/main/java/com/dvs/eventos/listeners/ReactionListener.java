package com.dvs.eventos.listeners;

import com.dvs.eventos.model.Event;
import com.dvs.eventos.model.EventStatus;
import com.dvs.eventos.service.EventService;
import com.dvs.eventos.service.SelectionService;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {

        if (event.getUser().isBot()) return;

        Event current = EventService.getCurrentEvent();
        if (current == null) return;

        if (!event.getMessageId().equals(current.getMessageId())) return;

        boolean reachedLimit = SelectionService.tryAddParticipant(
                event.getUserId(),
                event.getUser().getName()
        );

        if (reachedLimit) {
            event.getChannel().sendMessage("🚫 Evento fechado! 15 participantes selecionados.").queue();
        }
    }
}