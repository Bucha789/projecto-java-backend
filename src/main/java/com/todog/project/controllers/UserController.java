package com.todog.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todog.project.models.User;
import com.todog.project.services.UserService;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

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
}
