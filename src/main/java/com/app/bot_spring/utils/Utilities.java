package com.app.bot_spring.utils;

import net.dv8tion.jda.api.entities.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Utilities {

    private Utilities() {
        throw new IllegalStateException("Utilities class");
    }

    /**
     * Retorna un mensaje de despedida para el lunes y el viernes.
     * */
    public static String getMessageDay(int day){
        String message;
        switch (day){
            case 1:
                message = Constantes.MESSAGE_MONDAY;
                break;
            case 5:
                message = Constantes.MESSAGE_FRIDAY;
                break;
            default:
                message = "";
                break;
        }
        return message;
    }

    /**
     * Método que retorna un mensaje dependiendo del día de la semana.
     * */
    public static String getMessageOfDay(){
        String message;
        switch (getDayOfWeek()){
            case 1:
                message = Constantes.MONDAY;
                break;
            case 3:
                message = Constantes.WEDNESDAY;
                break;
            case 5:
                message = Constantes.FRIDAY;
                break;
            default:
                message = "";
                break;
        }
        return message;
    }

    /**
     * Método que retorna la fecha actual en formato `yyyy-MM-dd HH:mm:ss`.
     * **/
    public static String getformatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }

    /**
     * Método que retorna el día de la semana actual.
     * */
    public static int getDayOfWeek(){
        LocalDate currentDate = LocalDate.now();
        //return currentDate.getDayOfWeek().getValue();
        return 1;
    }

    /**
     * Método que retorna `true` o `false` dependiendo de si el rol es válido o no.
     * */
    public static boolean validRole(List<Role> roles) {
        boolean isValid = true;
        for (Role role : roles) {
            if (role.getName().equals("administrador") || role.getName().equals("program manager")) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }
}
