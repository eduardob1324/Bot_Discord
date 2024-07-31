package com.app.bot_spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class BotSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotSpringApplication.class, args);
	}
}
