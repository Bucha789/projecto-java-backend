package com.todog.project.helpers;

public class TaskInfo {
  private String title;
  private int id;
  private String description;
  private boolean status;
  private int userId;

  // Constructor, getters y setters
  // ...

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }
  public int getId() {
    return id;
  }
  
}

