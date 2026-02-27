package com.dvs.eventos.listeners;

import com.dvs.eventos.service.EventService;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Map;

import static com.dvs.eventos.service.EventService.createEvent;

public class CommandListener extends ListenerAdapter {

    private final Map<Long, String> etapaUsuario = new HashMap<>();
    private final Map<Long, String> horarioUsuario = new HashMap<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;
        if (event.getMember() == null) return;

        long userId = event.getAuthor().getIdLong();
        String mensagem = event.getMessage().getContentRaw();

        // 📌 Comando inicial
        if (mensagem.equalsIgnoreCase("!evento")) {
            etapaUsuario.put(userId, "HORARIO");
            event.getChannel().sendMessage("🕒 Informe o Horário do evento:").queue();
            return;
        }

        // 📌 Etapa Horário
        if ("HORARIO".equals(etapaUsuario.get(userId))) {
            horarioUsuario.put(userId, mensagem);
            etapaUsuario.put(userId, "IP");
            event.getChannel().sendMessage("🌐 Informe o IP do servidor:").queue();
            return;
        }

        // 📌 Etapa IP
        if ("IP".equals(etapaUsuario.get(userId))) {

            String horario = horarioUsuario.get(userId);
            String ip = mensagem;

            etapaUsuario.remove(userId);
            horarioUsuario.remove(userId);

            String textoEvento = """
**[Evento]  ~ [Gladiador]**

Horário: %s
Ip: %s

@everyone
""".formatted(horario, ip);

            event.getChannel().sendMessage(textoEvento)
                    .queue(message -> {

                        EventService.createEvent(
                                message.getId(),
                                event.getChannel().getId(),
                                horario,
                                ip
                        );

                        message.addReaction(Emoji.fromUnicode("✅")).queue();

                        // 🔥 ENVIA LOG AQUI
                        EventService.sendLog(
                                event.getJDA(),
                                "📢 Novo evento criado por " + event.getAuthor().getName() +
                                        "\nHorário: " + horario +
                                        "\nIP: " + ip
                        );
                    });
        }
    }
}