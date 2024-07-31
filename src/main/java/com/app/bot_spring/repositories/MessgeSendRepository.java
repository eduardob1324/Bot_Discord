package com.app.bot_spring.repositories;

import com.app.bot_spring.entities.MessageSend;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MessgeSendRepository extends CrudRepository<MessageSend, Long> {

    @Query("SELECT ms from MessageSend ms where ms.userName=?1 and ms.answerValidation='1'")
   Optional<MessageSend> findByUserName(String userName);

    Iterable<MessageSend> findAllByAnswerValidation(char answerValidation);

}
