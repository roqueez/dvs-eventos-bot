package com.dvs.eventos.listeners;

import com.dvs.eventos.service.EventService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;

        if (event.getMessage().getContentRaw().equalsIgnoreCase("!evento")) {

            event.getChannel().sendMessage("🎮 Evento aberto! Reaja para participar!")
                    .queue(message -> {
                        EventService.createEvent(message.getId());
                        message.addReaction("✅").queue();
                    });
        }
    }
}