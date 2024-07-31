package com.app.bot_spring.listeners;

import com.app.bot_spring.controllers.BotController;
import com.app.bot_spring.utils.Constantes;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class ButtonClickListener  extends ListenerAdapter {

    private BotController botController;

    @Autowired
    public ButtonClickListener(@Lazy BotController botController) {
        this.botController = botController;
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {

        String buttonId = event.getButton().getId();
        if(buttonId.equals("yes")) {
            event.reply(Constantes.NEEDS_HELP).queue();
        }else {
            botController.updateQuestion(event.getUser(), " ", '2');
            event.reply(Constantes.NOT_NEEDS_HELP).queue();
        }

    }

}
