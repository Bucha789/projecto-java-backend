package com.todog.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.todog.project.interfaces.IUser;
import com.todog.project.interfaces.IUserService;
import com.todog.project.models.User;

@Service
@EnableJpaRepositories("com.todog.interfaces")
public class UserService implements IUserService {
  @Autowired
  private IUser userRepo;

  @Override
  public User saveUser(String username, String password) throws IllegalArgumentException {
    try {
      Iterable<User> users = userRepo.findAll();
      for (User user : users) {
        if (user.getUsername().equals(username)) {
          throw new IllegalArgumentException("Username already exists");
        }
      }
      User newUser = userRepo.save(new User(username, password));
      return newUser;
    } catch(IllegalArgumentException e) {
      System.out.println("Error saving user: " + e.getMessage());
      return null;
    }
  }
  @Override
  public User getUser(String username) {
    System.out.println("Getting user...");
    Iterable<User> users = userRepo.findAll();
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }
    return null;
  }
  @Override
  public Iterable<User> getAllUsers() {
    return userRepo.findAll();
  }
}
