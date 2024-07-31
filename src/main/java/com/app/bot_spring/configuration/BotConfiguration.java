package com.app.bot_spring.configuration;



import com.app.bot_spring.listeners.ButtonClickListener;
import com.app.bot_spring.listeners.MessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;


@Configuration
public class BotConfiguration {

    @Value("${discord.token}")
    private String token;



    private final MessageListener messageListener;
    private final ButtonClickListener buttonClickListener;

    public BotConfiguration(MessageListener messageListener, ButtonClickListener buttonClickListener) {
        this.messageListener = messageListener;
        this.buttonClickListener = buttonClickListener;
    }

    @Bean
    public JDABuilder jdaBuilder() {
        return JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES);
    }

    @Bean
    public JDA jda() throws LoginException {
        JDABuilder jdaBuilder = jdaBuilder();
        return jdaBuilder.addEventListeners(messageListener, buttonClickListener ).build();
    }

}

