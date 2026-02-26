package com.dvs.eventos;

import com.dvs.eventos.listeners.CommandListener;
import com.dvs.eventos.listeners.ReactionListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class DvsEventosBot {

    public static void main(String[] args) throws Exception {

        String token = "COLOQUE_SEU_TOKEN_AQUI";

        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(new CommandListener())
                .addEventListeners(new ReactionListener())
                .build();

        jda.awaitReady();

        System.out.println("DvS Eventos online 🚀");
    }
}