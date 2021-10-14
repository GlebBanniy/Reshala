package com.example.webtest.controllers;

import com.example.webtest.models.Message;
import com.example.webtest.repos.MessageRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private MessageRep messageRepository;

    @Value("${upload.path}")
    private String uploadpath;

    @GetMapping
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Message> messages;
        if(filter != null && !filter.isEmpty()){
            messages = messageRepository.findByTag(filter);
        } else{
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "tasks";
    }

    @GetMapping("/{id}")
    public String showTask(@PathVariable Long id, Model model){
        Message message = messageRepository.findById(id).get();
        model.addAttribute("message", message);
        return "task";
    }
}
