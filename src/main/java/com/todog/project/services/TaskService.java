package com.todog.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todog.project.interfaces.ITask;
import com.todog.project.interfaces.ITaskService;
import com.todog.project.interfaces.IUser;
import com.todog.project.models.Task;
import com.todog.project.models.User;

@Service
public class TaskService implements ITaskService {
  @Autowired 
  ITask taskRepository;

  @Autowired
  IUser userRepository;

  @Override
  public Task saveTask(String title, String description, boolean status, int userId) {
    System.out.println("Saving task...");
    try {
      User currentUser = userRepository.findById(userId).get();
      if (currentUser != null){
        Task newTask = new Task(title, description, status);
        System.out.println("hola");
        newTask.setUser(currentUser);
        Task saveTask = taskRepository.save(newTask);
        return saveTask;
      }
      return null;
    } catch(IllegalArgumentException e) {
      System.out.println("Error saving task: " + e.getMessage());
      return null;
    }
  }

  @Override
  public Task getTask(int id) {
    return taskRepository.findById(id).get();
  }

  @Override
  public Iterable<Task> getAllTaskByUser(int userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      List<Task> tasks = user.getTasks();
      return tasks;
    } else {
      System.out.println("User not found");
      return null;
    }
  }

}
