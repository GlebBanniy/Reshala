package com.example.webtest.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String text;
    private String category;
    private String tag;
    private double rating;
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String answer;
    private String filename;

    /*private String getAuthorName(){
        return author != null ? author.getUsername() : "&ltnone&gt";
    }*/



    public Message() {
    }

    public Message(String name, String text, String category, String tag, double rating, User author, String answer) {
        this.name = name;
        this.text = text;
        this.category = category;
        this.tag = tag;
        this.rating = rating;
        this.author = author;
        this.answer = answer;
    }

/*    public Message(String name, String text, String category, String tag, User user) {
        this.name = name;
        this.text = text;
        this.category = category;
        this.tag = tag;
        this.author = user;
    }*/

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
