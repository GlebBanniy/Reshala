package com.example.webtest.controllers;

import com.example.webtest.models.Message;
import com.example.webtest.models.TaskRating;
import com.example.webtest.models.TaskRatingKey;
import com.example.webtest.models.User;
import com.example.webtest.repos.MessageRep;
import com.example.webtest.repos.TaskRatingRepo;
import com.example.webtest.repos.UserRepo;
import com.example.webtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private MessageRep messageRepository;

    @Autowired
    private TaskRatingRepo taskRatingRepo;

    @Value("${upload.path}")
    private String uploadpath;

    @GetMapping
    public String main(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false, defaultValue = "date") String sort,
            Model model){
        Iterable<Message> messages;
        if(filter != null && !filter.isEmpty()){
            messages = messageRepository.findByTag(filter);
        } else{
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        model.addAttribute("sort", sort);
        model.addAttribute("profile", user);
        return "tasks";
    }

    @GetMapping("/{message}")
    public String showTask(
            @AuthenticationPrincipal User user,
            @PathVariable Message message,
            Model model){
        //Message message = messageRepository.findById(id).get();
        boolean isSolved = false;
        if (user != null) {
            isSolved = message.findSolver(user);
        }
        model.addAttribute("isSolved", isSolved);
        model.addAttribute("message", message);
        model.addAttribute("rating", message.getResultRating());
        return "task";
    }

    @PostMapping("/{message}")
    public String checkAnswer(
            @AuthenticationPrincipal User user,
            @PathVariable Message message,
            @RequestParam(required = false) String answer,
            @RequestParam(required = false) Integer rating,
            Model model){
        if (rating!=null){
            TaskRating tr = new TaskRating();
            tr.setMessage(message);
            tr.setUser(user);
            tr.setRating(rating);
            TaskRatingKey trk = new TaskRatingKey();
            trk.setTaskId(message.getId());
            trk.setUserId(user.getId());
            tr.setId(trk);
            double i=message.getResultRating();
            taskRatingRepo.save(tr);
            double j=message.getResultRating();
            message.setRating(j);
            messageRepository.save(message);
            return "redirect:/tasks/"+message.getId().toString();
        }
        boolean flag = false;
        if (answer.equals(message.getAnswer1()))
            flag=true;
        if (answer.equals(message.getAnswer2()))
            flag=true;
        if (answer.equals(message.getAnswer3()))
            flag=true;

        if (flag){
            message.getTaskSolvers().add(user);

            //userRepo.save(user);
            messageRepository.save(message);
        }
        boolean isSolved = message.findSolver(user);
        //messageRepository.save(message);
        model.addAttribute("isSolved", isSolved);
        model.addAttribute("message", message);
        model.addAttribute("flag", flag);
        return "task";
    }
}
