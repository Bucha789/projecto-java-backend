package com.todog.project.interfaces;

import com.todog.project.models.User;

public interface IUserService {
  public User saveUser(String username, String password);
  public User getUser(String username);
  public Iterable<User> getAllUsers();
}
