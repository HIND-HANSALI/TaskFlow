package com.taskflow.service;

import com.taskflow.dto.TaskDTO;
import com.taskflow.dto.response.TaskResponseDTO;
import com.taskflow.models.Task;

import java.util.List;

public interface TaskService  {
    TaskResponseDTO createTask(TaskDTO taskDTO);
    void updateTaskStatusDone(Long taskId);
//    List<Task> getAllTasks();

    List<TaskResponseDTO> getAllTasks();
    TaskResponseDTO getTaskById(Long Id);


}
