package com.todog.project.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todog.project.models.Task;

@Repository
public interface ITask extends CrudRepository<Task, Integer> {

}
