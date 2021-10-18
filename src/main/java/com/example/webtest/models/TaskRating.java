package com.example.webtest.models;

import javax.persistence.*;

@Entity
@Table(name = "task_rating")
public class TaskRating {

    @EmbeddedId
    private TaskRatingKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id")
    private Message message;

    @Column(name = "rating")
    private int rating;

    public TaskRating(User user, Message message, int rating) {
        this.user = user;
        this.message = message;
        this.rating = rating;
    }

    public TaskRating() {}

    public TaskRatingKey getId() {
        return id;
    }

    public void setId(TaskRatingKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
