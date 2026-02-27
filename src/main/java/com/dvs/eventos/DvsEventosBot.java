package com.dvs.eventos;

import com.dvs.eventos.listeners.CommandListener;
import com.dvs.eventos.listeners.ReactionListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class DvsEventosBot {

    public static void main(String[] args) throws Exception {

        String token = System.getenv("TOKEN");

        if (token == null || token.isEmpty()) {
            System.out.println("❌ Token não configurado.");
            return;
        }

        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS
                )
                .addEventListeners(
                        new CommandListener(),
                        new ReactionListener()
                )
                .build();

        jda.awaitReady();

        System.out.println("DvS Eventos online 🚀");
    }
}