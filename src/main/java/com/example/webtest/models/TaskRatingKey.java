package com.example.webtest.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TaskRatingKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "task_id")
    Long taskId;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public TaskRatingKey(Long userId, Long taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public TaskRatingKey() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
