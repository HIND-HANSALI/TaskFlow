package com.taskflow.controller;


import com.taskflow.dto.TaskDTO;
import com.taskflow.dto.response.TagResponseDTO;
import com.taskflow.dto.response.TaskResponseDTO;
import com.taskflow.handlers.response.ResponseMessage;
import com.taskflow.mappers.TaskMapper;
import com.taskflow.models.Task;
import com.taskflow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllTasks();
        if(tasks .isEmpty()) {
            return ResponseMessage.notFound("Task not found");
        }else {
            return ResponseMessage.ok(tasks , "Success");
        }

    }
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long taskId) {
        TaskResponseDTO task = taskService.getTaskById(taskId);
        if(task== null) {
            return ResponseMessage.notFound("task not found");
        }else {
            return ResponseMessage.ok(task, "Success");
        }

    }
    @PostMapping
    public ResponseEntity createTask(@RequestBody @Valid TaskDTO task) {

        TaskResponseDTO createdTask =taskService.createTask(task);
        if(createdTask !=null){
            return ResponseMessage.created(createdTask ,"Task Created Successfully");
        }else{
            return ResponseMessage.badRequest("Failed To Create Task");
        }

    }






}
