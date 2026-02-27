package com.dvs.eventos.listeners;

import com.dvs.eventos.model.Event;
import com.dvs.eventos.service.EventService;
import com.dvs.eventos.service.SelectionService;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionListener extends ListenerAdapter {

    private static final String EMOJI = "✅";

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {

        if (event.getUser() == null || event.getUser().isBot()) return;

        Event current = EventService.getCurrentEvent();
        if (current == null) return;

        // verifica se é a mensagem do evento
        if (!event.getMessageId().equals(current.getMessageId())) return;

        // verifica se é o emoji correto
        if (!event.getEmoji().getName().equals(EMOJI)) return;

        boolean reachedLimit = SelectionService.tryAddParticipant(
                event.getUserId(),
                event.getUser().getName()
        );

        // atualiza lista no canal de logs
        EventService.updateParticipantsLog(event.getJDA());

        // se atingiu limite, limpa reações
        if (reachedLimit) {
            event.getChannel()
                    .retrieveMessageById(current.getMessageId())
                    .queue(message -> message.clearReactions().queue());
        }
    }

    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {

        Event current = EventService.getCurrentEvent();
        if (current == null) return;

        if (!event.getMessageId().equals(current.getMessageId())) return;

        if (!event.getEmoji().getName().equals(EMOJI)) return;

        SelectionService.removeParticipant(event.getUserId());

        // atualiza lista no canal de logs
        EventService.updateParticipantsLog(event.getJDA());
    }
}