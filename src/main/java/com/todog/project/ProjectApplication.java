package com.todog.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
import com.todog.project.models.User;
import com.todog.project.services.TaskService;
import com.todog.project.services.UserService;

@SpringBootApplication(scanBasePackages = {"com.todog"})
@EnableJpaRepositories("com.todog.project.interfaces")
@RestController
public class ProjectApplication {
  @Autowired
  private UserService userService;

  @Autowired
  private TaskService taskService;
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	@GetMapping("/hello")
  public ResponseEntity<?> hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return ResponseEntity.status(HttpStatus.OK).body(String.format("Hello %s!", name));
  }

  @PostMapping("/user")
  public ResponseEntity<User> createUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
    User userCreated = userService.saveUser(username, password);
    if (userCreated == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }
  @GetMapping("/user")
  public ResponseEntity<User> getUser(@RequestParam(value = "username") String username) {
    User user = userService.getUser(username);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @PostMapping("/task")
  public ResponseEntity<Task> saveTask(@RequestBody TaskInfo taskInfo) { 
    Task taskCreated = taskService.saveTask(taskInfo.getTitle(), taskInfo.getDescription(), taskInfo.isStatus(), taskInfo.getUserId());
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
    Task taskUpdated = taskService.updateTask(taskInfo.getId(), taskInfo.getTitle(), taskInfo.getDescription(), taskInfo.isStatus());
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
