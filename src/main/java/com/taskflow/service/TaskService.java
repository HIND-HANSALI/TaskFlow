package com.taskflow.service;

import com.taskflow.models.Task;

import java.util.List;

public interface TaskService  {

    Task createTask(Task task);
    List<Task> getAllTasks();
    Task getTaskById(Long Id);
    Task saveTask(Task task);

}
