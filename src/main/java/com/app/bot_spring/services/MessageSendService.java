package com.app.bot_spring.services;

import com.app.bot_spring.entities.MessageSend;
import com.app.bot_spring.repositories.MessgeSendRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
@Primary
public class MessageSendService {

    private final MessgeSendRepository messgeSendRepository;

    public MessageSendService(MessgeSendRepository messgeSendRepository) {
        this.messgeSendRepository = messgeSendRepository;
    }

    @Transactional
    public void save(MessageSend message) {
        messgeSendRepository.save(message);
    }


   @Transactional(readOnly = true)
    public Optional<MessageSend> findByUserName(String userName) {
        return messgeSendRepository.findByUserName(userName);
    }

    @Transactional(readOnly = true)
    public Iterable<MessageSend> findAllAnswerValidation(char answerValidation) {
        return messgeSendRepository.findAllByAnswerValidation(answerValidation);
    }

}
