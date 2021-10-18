package com.example.webtest.controllers;

import com.example.webtest.models.Message;
import com.example.webtest.models.User;
import com.example.webtest.repos.MessageRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/office")
public class UsersTaskController {
    @Autowired
    private MessageRep messageRepository;

    @Value("${upload.path}")
    private String uploadpath;

    @GetMapping("/user/{userid}/edit/{id}")
    public String editTask(@PathVariable("id") Long id,
                           @PathVariable("userid") User user,
                           Model model){
        Message message = messageRepository.findById(id).get();
        model.addAttribute("message", message);
        model.addAttribute("thisUser", user);
        return "edit";
    }

    @GetMapping("/user/{userid}/delete/{messageid}")
    public String delete(
            @PathVariable("userid") User user,
            @PathVariable("messageid") Message message){
        messageRepository.delete(message);
        return "redirect:/office/user/"+user.getId().toString();
    }


    @PostMapping("/user/{userid}/edit/{id}")
    public String update(
            @PathVariable("userid") User user,
            @PathVariable("id") Long id,
            @RequestParam String name,
            @RequestParam String text,
            @RequestParam String category,
            @RequestParam String tag,
            @RequestParam String answer1,
            @RequestParam String answer2,
            @RequestParam String answer3,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Message message = messageRepository.findById(id).get();
        message.setName(name);
        message.setText(text);
        message.setCategory(category);
        message.setTag(tag);
        message.setAnswer1(answer1);
        message.setAnswer2(answer2);
        message.setAnswer3(answer3);
        if (file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadpath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadpath + "/" + resultFilename));
            message.setFilename(resultFilename);
        }
        messageRepository.save(message);
        return "redirect:/office/user/"+user.getId().toString();
    }

    @GetMapping("/user/{userid}")
    public String showMyTasks(
            @PathVariable("userid") User user,
            Model model){
        Iterable<Message> messages = messageRepository.findByAuthor(user);
        model.addAttribute("messages", messages);
        model.addAttribute("thisUser", user);
        model.addAttribute("createdTasksCounter", messageRepository.findByAuthor(user).size());
        model.addAttribute("solvedTasksCounter", user.getSolvedTasks().size());

        return "office";
    }

    @GetMapping("/user/{userid}/preview_{messageid}")
    public String preview(@PathVariable("messageid") Long messageid, Model model){
        Message message = messageRepository.findById(messageid).get();
        model.addAttribute("message", message);
        return "preview";
    }

    @PostMapping("/user/{userid}")
    public String add(
            @PathVariable("userid") User user,
            @RequestParam String name,
            @RequestParam String text,
            @RequestParam String category,
            @RequestParam String tag,
            @RequestParam String answer1,
            @RequestParam String answer2,
            @RequestParam String answer3,
            @RequestParam("file") MultipartFile file,
            Model model) throws IOException {
        Message message = new Message(name, text, category, tag, 0.0, user, answer1, answer2, answer3);

        if (file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadpath);

            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadpath + "/" + resultFilename));
            message.setFilename(resultFilename);
        }

        message.setDate(new Date());

        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        model.addAttribute("thisUser", user);
        return "redirect:/office/user/"+user.getId().toString();
    }
}
