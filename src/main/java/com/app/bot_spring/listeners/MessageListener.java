package com.app.bot_spring.listeners;




import com.app.bot_spring.utils.Constantes;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class MessageListener extends ListenerAdapter {


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()){
            return;
        }
        Message message = event.getMessage();
        if (message.isFromType(ChannelType.PRIVATE ) && message.getContentDisplay().equals("ping")) {
            event.getAuthor().openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessage("pong!").queue();
            });
        }
    }




}

