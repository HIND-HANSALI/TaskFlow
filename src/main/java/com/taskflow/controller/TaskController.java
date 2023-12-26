package com.taskflow.controller;

import com.taskflow.dto.TaskDto;
import com.taskflow.dto.TaskRequestDTO;
import com.taskflow.handlers.response.ResponseMessage;
import com.taskflow.mappers.TaskMapper;
import com.taskflow.models.Task;
import com.taskflow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public ResponseEntity getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        if(tasks .isEmpty()) {
            return ResponseMessage.notFound("Task not found");
        }else {
            return ResponseMessage.ok(tasks , "Success");
        }
    }
    @PostMapping("/save")
    public ResponseEntity createTask(@RequestBody @Valid TaskRequestDTO taskRequestDTO) {
        LocalDate now = LocalDate.now();
        LocalDate endDate = taskRequestDTO.getEndDate();

        if (endDate != null && now.until(endDate, ChronoUnit.DAYS) > 3) {
            return ResponseMessage.badRequest("Task deadline cannot be more than 3 days in advance");
        }
        Task task = TaskMapper.mapTaskDtoToTask(taskRequestDTO);
        Task task1 = taskService.createTask(task);
        if (task1 == null) {
            return ResponseMessage.badRequest("Failed To Create Task");
        } else {
            return ResponseMessage.created(task1,"Task Created Successfully");
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
