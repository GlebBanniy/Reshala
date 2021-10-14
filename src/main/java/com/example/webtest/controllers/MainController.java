package com.example.webtest.controllers;

import com.example.webtest.models.Message;
import com.example.webtest.models.User;
import com.example.webtest.repos.MessageRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;


@Controller
public class MainController {

    @Autowired
    private MessageRep messageRepository;

    @Value("${upload.path}")
    private String uploadpath;

    @GetMapping("/")
    public String greeting(
            Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user, @RequestParam(required = false, defaultValue = "") String filter,  Model model){
        Iterable<Message> messages;
        if(filter != null && !filter.isEmpty()){
            messages = messageRepository.findByTag(filter);
        } else{
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        model.addAttribute("profile", user);
        return "main";
    }
}