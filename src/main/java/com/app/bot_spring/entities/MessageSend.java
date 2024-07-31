package com.app.bot_spring.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "messages")
public class MessageSend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Column(nullable = false, length = 50)
    private String question;

    @Column(name = "date_send", nullable = false, length = 35)
    private String dateSend;

    @Column(nullable = true )
    private String answer;

    @Column(name = "date_answer", nullable = true, length = 35)
    private String dateAnswer;

    @Column(name = "answer_validation", nullable = true)
    private char answerValidation;

    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "helpId", nullable = true)
    private HelpSend help;

    public MessageSend(String userName, String question, String dateSend, String answer, String dateAnswer, char answerValidation, HelpSend help) {
        this.userName = userName;
        this.question = question;
        this.dateSend = dateSend;
        this.answer = answer;
        this.dateAnswer = dateAnswer;
        this.answerValidation = answerValidation;
        this.help = help;
    }
}