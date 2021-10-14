package com.example.webtest.repos;

import com.example.webtest.models.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task, Long> {

}
