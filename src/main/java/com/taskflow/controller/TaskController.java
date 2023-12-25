package com.taskflow.controller;

import com.taskflow.dto.TaskDto;
import com.taskflow.handlers.response.ResponseMessage;
import com.taskflow.models.Task;
import com.taskflow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        if(tasks .isEmpty()) {
            return ResponseMessage.notFound("Task not found");
        }else {
            return ResponseMessage.ok(tasks , "Success");
        }
    }

    @PostMapping
    public ResponseEntity addTask(@Valid @RequestBody Task task) {
        Task taskSaved = taskService.saveTask(task);
        System.out.println("Received Task: " + taskSaved);

        if(taskSaved  == null) {
            return ResponseMessage.badRequest("Task not created");
        }else {
            return ResponseMessage.created(taskSaved , "Task created successfully");
        }
    }
}
