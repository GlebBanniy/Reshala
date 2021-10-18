package com.example.webtest.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "message")
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

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "users_solution",
            joinColumns = @JoinColumn(name = "solved_task"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> taskSolvers = new HashSet<>();

    @OneToMany(mappedBy = "message")
    private Set<TaskRating> ratings = new HashSet<>();

    private String answer1;
    private String answer2;
    private String answer3;
    private String filename;

    /*private String getAuthorName(){
        return author != null ? author.getUsername() : "&ltnone&gt";
    }*/



    public Message() {
    }

    public Message(String name, String text, String category, String tag, double rating, User author, String answer1, String answer2, String answer3) {
        this.name = name;
        this.text = text;
        this.category = category;
        this.tag = tag;
        this.rating = rating;
        this.author = author;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
    }

/*    public Message(String name, String text, String category, String tag, User user) {
        this.name = name;
        this.text = text;
        this.category = category;
        this.tag = tag;
        this.author = user;
    }*/

    public Set<TaskRating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<TaskRating> ratings) {
        this.ratings = ratings;
    }

    public double getResultRating(){
        double sum = 0.0;
        if (getRatings().size() == 0)
            return 0.0;
        for (Iterator<TaskRating> iterator = getRatings().iterator(); iterator.hasNext(); ){
            TaskRating current = iterator.next();
            sum += current.getRating();
        }
        return sum/getRatings().size();
    }

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

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public Set<User> getTaskSolvers() {
        return taskSolvers;
    }

    public void setTaskSolvers(Set<User> taskSolvers) {
        this.taskSolvers = taskSolvers;
    }

    public boolean findSolver(User user){
        Set<User> ts = getTaskSolvers();
        for (User tsUser : ts){
            if (tsUser.getId().equals(user.getId()))
                return true;
        }
        return false;
    }
}
