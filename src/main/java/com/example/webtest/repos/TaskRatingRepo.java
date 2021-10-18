package com.example.webtest.repos;

import com.example.webtest.models.TaskRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TaskRatingRepo extends JpaRepository<TaskRating,TaskRating> {

}
