package com.todog.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todog.project.helpers.TaskInfo;
import com.todog.project.models.Task;
import com.todog.project.services.TaskService;

@RestController
public class TaskController {

  @Autowired
  private TaskService taskService;

  @PostMapping("/task")
  public ResponseEntity<Task> saveTask(@RequestBody TaskInfo taskInfo) { 
    Task taskCreated = taskService.saveTask(taskInfo.getTitle(), taskInfo.getDescription(), taskInfo.isCompleted(), taskInfo.getUserId());
    if (taskCreated == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(taskCreated);
  }

  @GetMapping("/task")
  public ResponseEntity<Task> getTask(@RequestParam(value = "id") int id) {
    Task task = taskService.getTask(id);
    if (task == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(task);
  }

  @PutMapping("/task")
  public ResponseEntity<Task> updateTask(@RequestBody TaskInfo taskInfo) {
    Task taskUpdated = taskService.updateTask(taskInfo.getId(), taskInfo.getTitle(), taskInfo.getDescription(), taskInfo.isCompleted());
    if (taskUpdated == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(taskUpdated);
  }

  @DeleteMapping("/task")
  public ResponseEntity<?> deleteTask(@RequestParam(value = "id") int id) {
    taskService.deleteTask(id);
    return ResponseEntity.status(HttpStatus.OK).body("Task deleted");
  }

  @GetMapping("/tasks")
  public ResponseEntity<Iterable<Task>> getAllTasks(@RequestParam(value = "userId") int userId) {
    Iterable<Task> tasks = taskService.getAllTaskByUser(userId);
    if (tasks == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(tasks);
  }
}
