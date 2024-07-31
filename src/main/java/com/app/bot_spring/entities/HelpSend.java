package com.app.bot_spring.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "helping")
public class HelpSend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column( nullable = false , length = 50 )
    private String question ;

    @Column(name = "date_Needs_Help", nullable = false, length = 35)
    private String dateNeedsHelp;

    @Column(nullable = true)
    private String answer;

    public HelpSend(String question, String dateNeedsHelp, String answer) {
        this.question = question;
        this.dateNeedsHelp = dateNeedsHelp;
        this.answer = answer;
    }
}