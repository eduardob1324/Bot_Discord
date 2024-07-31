package com.app.bot_spring.controllers;

import com.app.bot_spring.entities.HelpSend;
import com.app.bot_spring.entities.MessageSend;
import com.app.bot_spring.services.MessageSendService;
import com.app.bot_spring.utils.Constantes;
import com.app.bot_spring.utils.Utilities;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class BotController {

    @Value("${discord.server}")
    private String dicordServer;

    private final JDA jda;
    private final MessageSendService messageSendService;
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public BotController(JDA jda, MessageSendService messageSendService) {
        this.messageSendService = messageSendService;
        this.jda = jda;

    }

    /*
     * Método que envía un mensaje privado
     * y espera por una respuesta del usuario.
     * */
    public void sendMessageAndWaitForResponse(User user){
        if (!Utilities.getMessageOfDay().isBlank()){
            user.openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessage(Utilities.getMessageOfDay()).queue();
                saveQuestion(user);

                jda.addEventListener(new ListenerAdapter() {
                    @Override
                    public void onMessageReceived(MessageReceivedEvent event) {
                        if (event.getAuthor().equals(user)) {
                            Message message = event.getMessage();
                            String response = message.getContentDisplay();
                            jda.removeEventListener(this);

                            if(Utilities.getDayOfWeek() == 3 || Utilities.getDayOfWeek() == 4){
                                updateQuestion(user, response, '1');
                                sendMessageAndWaitForResponseWednesday(user);
                            }else{
                                user.openPrivateChannel().queue(privateChannel ->
                                        privateChannel.sendMessage(Utilities.getMessageDay(Utilities.getDayOfWeek())).queue());
                                updateQuestion(user, response, '2');
                            }
                        }
                    }
                });
            });
        }else {
            logger.warning(Constantes.ERROR_QUESTION);
        }
    }

     /*
     * Método que envía un mensaje preguntando al usuario si necesita ayuda
     * y espera por una respuesta solo los días miércoles.
     * */
    private void sendMessageAndWaitForResponseWednesday(User user) {
        user.openPrivateChannel().queue(privateChannel -> {
            privateChannel.sendMessage(Constantes.HELP).
                    setActionRow(
                            Button.primary("yes", "Sí"),
                            Button.danger("no", "No")
                    ).queue();
            jda.addEventListener(new ListenerAdapter() {
                @Override
                public void onMessageReceived(MessageReceivedEvent event) {
                    if (event.getAuthor().equals(user)) {
                        Message message = event.getMessage();
                        String response = message.getContentDisplay();
                        jda.removeEventListener(this);
                        user.openPrivateChannel().queue(privateChannel1 ->
                                privateChannel1.sendMessage(Constantes.NEEDS_HELP_PLEASE).queue()
                        );
                        updateAnswerHelp(user, response);
                        sendMesageForAdmin(user, response);
                    }
                }
            });
        });
    }

    /*
    * Método que envía un mensaje privado a los administradores
    * solo si el usuario solicita ayuda, se envía los días miércoles.
     * */
    private void sendMesageForAdmin(User user, String help) {
        String message = "El usuario: ".concat(" ").concat(user.getName().concat(" solicita ayuda con: ").concat(help));
        Guild server = jda.getGuildById(dicordServer);
        if (server == null) {
            logger.warning(Constantes.ERROR_SERVER);
            return;
        }
        server.loadMembers().onSuccess(members -> {
            for (Member member : members) {
                User user1 = member.getUser();
                if (!Utilities.validRole(member.getRoles())){
                    user1.openPrivateChannel().queue(privateChannel ->
                            privateChannel.sendMessage(message).queue()
                    );
                }
            }
        }).onError(Throwable::printStackTrace);
    }

    /*
     * Método que guarda la pregunta que se le realiza a los usuarios del servidor.
     * */
    private void saveQuestion(User user){
        LocalDateTime date = LocalDateTime.now();
        MessageSend message = new MessageSend(user.getName(),Utilities.getMessageOfDay(),Utilities.getformatDate(date),null,null,'1',null );
        messageSendService.save(message);
    }

    /*
     * Método que guarda la respuesta con la ayuda que solicitó el usuario.
     * */
    private void updateAnswerHelp(User user, String response ){
        LocalDateTime date = LocalDateTime.now();
        Optional<MessageSend> ms = messageSendService.findByUserName(user.getName());
        ms.ifPresent(messageSend -> {
            HelpSend helpSend = new HelpSend(Constantes.NEEDS_HELP, Utilities.getformatDate(date), response);
            messageSend.setHelp(helpSend);
            messageSend.setAnswerValidation('2');
            messageSendService.save(messageSend);
        });
    }

    /*
     * Método que actualiza la pregunta que se le realiza al usuario con la respuesta enviada.
     * */
    public void updateQuestion(User user, String response, char answerValidation ){
        LocalDateTime date = LocalDateTime.now();
        Optional<MessageSend> ms = messageSendService.findByUserName(user.getName());
        ms.ifPresent(messageSend -> {
            if(!response.equals(" ")){
                messageSend.setAnswer(response);
            }
            messageSend.setDateAnswer(Utilities.getformatDate(date));
            messageSend.setAnswerValidation(answerValidation);
            messageSendService.save(messageSend);
        });
    }


}
