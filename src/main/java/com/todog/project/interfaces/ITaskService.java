package com.todog.project.interfaces;

import com.todog.project.models.Task;

public interface ITaskService {
  public Task saveTask(String title, String description, boolean status, int userId);
  public Task getTask(int id);
  public Iterable<Task> getAllTaskByUser(int userId);
}
