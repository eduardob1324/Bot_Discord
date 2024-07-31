package com.app.bot_spring.tasks;

import com.app.bot_spring.controllers.BotController;
import com.app.bot_spring.entities.MessageSend;
import com.app.bot_spring.services.MessageSendService;
import com.app.bot_spring.utils.Constantes;
import com.app.bot_spring.utils.Utilities;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ScheduledTask extends ListenerAdapter {
    /**
     *  @Value
     * se establece el valor del servidor
     * */
    @Value("${discord.server}")
    private String dicordServer;

    private final JDA jda;
    private final MessageSendService messageSendService;
    private final BotController botController;
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public ScheduledTask(JDA jda, MessageSendService messageSendService, BotController botController ) {
        this.botController = botController;
        this.messageSendService = messageSendService;
        this.jda = jda;

    }

    /**@Scheduled(fixedRate = 60000)  Envía un mensaje cada 60 segundos (60000 milisegundos)
     * establecemos la hora a la que se enviaran los mensajes
     * */

    /**
     * Tarea programada que envía un mensaje a los usuarios del servidor los días
     * lunes, miércoles y viernes
     * */

    //@Scheduled(cron = "1 01 08 * * MON,WED,FRI")
    @Scheduled(cron = "2 * * * * *")
    private void sendScheduledMessageOfDay() {
        Guild server = jda.getGuildById(dicordServer);
        if (server == null) {
            logger.warning(Constantes.ERROR_SERVER);
            return;
        }
        server.loadMembers().onSuccess(members -> {
            for (Member member : members) {
                if (!member.isBoosting() && Utilities.validRole(member.getRoles()) && !member.getUser().getName().equals("bot-app") ){
                    botController.sendMessageAndWaitForResponse(member.getUser());
                }
            }
        }).onError(Throwable::printStackTrace);
    }

    /**
     * Tarea programada que actualiza el campo answerValidation cuando los usuarios no proporcionan
     * una respuesta. Esta tarea se ejecuta los días martes, jueves y domingo.
     **/


    //@Scheduled(cron = "1 01 22 * * TUE,THU,SUN")
    @Scheduled(cron = "1 * * * * *")
    private void sendScheduledUpdateResponseUser() {
        Iterable<MessageSend> messageSends = messageSendService.findAllAnswerValidation('1');
        for (MessageSend messageSend : messageSends) {
            messageSend.setAnswerValidation('2');
            messageSendService.save(messageSend);
        }
    }
}

