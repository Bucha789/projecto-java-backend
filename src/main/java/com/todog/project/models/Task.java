
package com.todog.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;
  private String description;
  private boolean completed;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Task() {}

  public Task(String title, String description, boolean completed) {
    super();
    this.title = title;
    this.description = description;
    this.completed = completed;
  }

  //getters and setters
  public int getId() {
    return id;
  }
  public String getTitle() {
    return title;
  }
  public String getDescription() {
    return description;
  }
  public boolean isCompleted() {
    return completed;
  }
  public void setId(int id) {
    this.id = id;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
}
