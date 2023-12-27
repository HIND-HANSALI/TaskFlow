package com.taskflow.service;

import com.taskflow.dto.TaskDTO;
import com.taskflow.dto.response.TaskResponseDTO;
import com.taskflow.models.Task;

import java.util.List;

public interface TaskService  {

//    Task createTask(Task task);
    TaskResponseDTO createTask(TaskDTO taskDTO);

    List<TaskResponseDTO> getAllTasks();
    TaskResponseDTO getTaskById(Long Id);
    Task saveTask(Task task);

}
