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
      Optional<User> user = userRepository.findById(userId);

      if (user.isPresent()){
        User currentUser = user.get();
        Task newTask = new Task(title, description, status);
        newTask.setUser(currentUser);
        Task taskCreated = taskRepository.save(newTask);
        return taskCreated;
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

  @Override
  public Task updateTask(int id, String title, String description, boolean status) {
    try {
      Optional<Task> optionalTask = taskRepository.findById(id);
      if (optionalTask.isPresent()) {
        Task task = optionalTask.get();
        task.setTitle(title);
        task.setDescription(description);
        task.setCompleted(status);
        Task taskUpdated = taskRepository.save(task);
        return taskUpdated;
      } else {
        System.out.println("Task not found");
        return null;
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Error updating task: " + e.getMessage());
      return null;
    }
  }
  @Override
  public void deleteTask(int id) {
    try {
      Optional<Task> optionalTask = taskRepository.findById(id);
      if (optionalTask.isPresent()) {
        Task task = optionalTask.get();
        taskRepository.delete(task);
      } else {
        System.out.println("Task not found");
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Error deleting task: " + e.getMessage());
    }
  }
}
