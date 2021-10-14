package com.example.webtest.repos;

import com.example.webtest.models.Message;
import com.example.webtest.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRep extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);
    List<Message> findByAuthor(User author);
}
