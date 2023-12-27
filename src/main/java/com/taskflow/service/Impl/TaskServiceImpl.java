package com.taskflow.service.Impl;

import com.taskflow.dto.TaskDTO;
import com.taskflow.dto.response.TaskResponseDTO;
import com.taskflow.enums.Role;
import com.taskflow.enums.StatusRequest;
import com.taskflow.enums.TaskStatus;
import com.taskflow.handlers.exception.OperationException;
import com.taskflow.handlers.exception.ResourceNotFoundException;
import com.taskflow.mappers.TaskMapper;
import com.taskflow.models.Tag;
import com.taskflow.models.Task;
import com.taskflow.models.User;
import com.taskflow.repository.TagRepository;
import com.taskflow.repository.TaskChangeRepository;
import com.taskflow.repository.TaskRepository;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.TaskService;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;
    private final TaskChangeRepository taskChangeRepository;

    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

//    public TaskServiceImpl(TaskRepository taskRepository, TagRepository tagRepository, TaskMapper taskMapper,UserRepository userRepository) {
//        this.taskRepository = taskRepository;
//        this.tagRepository=tagRepository;
//        this.taskMapper = taskMapper;
//        this.userRepository=userRepository;
//
//    }

//    @Override
//    public List<Task> getAllTasks() {
//        return taskRepository.findAll();
//    }

    @Override
    public List<TaskResponseDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> {
                    TaskResponseDTO taskResponseDTO = taskMapper.entityToDto(task);
                    return taskResponseDTO;
                })
                .toList();
    }
    @Override
    public TaskResponseDTO getTaskById(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            TaskResponseDTO taskResponseDTO = taskMapper.entityToDto(task);
    //        taskResponseDTO.setAssignedTo(mapUserToUserDTO(task.getAssignedTo()));
    //        taskResponseDTO.setCreatedBy(mapUserToUserDTO(task.getCreatedBy()));
            return taskResponseDTO;
        } else {
            throw new ResourceNotFoundException("Task not found with ID: " + taskId);
        }
    }

    @Override
    public TaskResponseDTO createTask(TaskDTO taskDTO) {
        validateTask(taskDTO);

        User createdByUser = userRepository.findById(taskDTO.getCreatedByUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + taskDTO.getCreatedByUserId()));

        User assignedToUser = userRepository.findById(taskDTO.getAssignedToUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + taskDTO.getAssignedToUserId()));

        Task task = taskMapper.dtoToEntity(taskDTO);
        task.setCreatedBy(createdByUser);
        task.setAssignedTo(assignedToUser);
        task.setTags(getTagsByIds(taskDTO.getTagIds()));
        task.setTaskStatus(TaskStatus.InProgress);
        Task savedTask = taskRepository.save(task);
        return taskMapper.entityToDto(savedTask);
    }
    private void validateTask(TaskDTO taskDTO) {
        LocalDate currentDate = LocalDate.now();

        if (taskDTO.getStartDate().isBefore(currentDate)) {
            throw new ValidationException("Task start date cannot be in the past.");
        }

        if (taskDTO.getEndDate().isBefore(taskDTO.getStartDate())) {
            throw new ValidationException("Task end date cannot be in the past.");
        }

        if (taskDTO.getStartDate().isAfter(currentDate.plusDays(3))) {
            throw new ValidationException("Schedule the task with at least a 3-day notice in advance.");
        }

        getTagsByIds(taskDTO.getTagIds());

        if (taskDTO.getTagIds().size() < 2) {
            throw new ValidationException("At least two tags are required for the task.");
        }

        getUserById(taskDTO.getCreatedByUserId());
        getUserById(taskDTO.getAssignedToUserId());

        if (getUserById(taskDTO.getCreatedByUserId()).getRole() == Role.USER) {
            if (taskDTO.getCreatedByUserId() != taskDTO.getAssignedToUserId()) {
                throw new ValidationException("User with role USER can only assign tasks to themselves.");
            }
        }
    }
    private List<Tag> getTagsByIds(List<Long> tagIds) {
        List<Tag> existingTags = tagRepository.findAllById(tagIds);
        if (existingTags.size() != tagIds.size()) {
            throw new ValidationException("One or more tags do not exist.");
        }
        return existingTags;
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    }





    @Override
    public Task saveTask(Task task){
//        validateTask(task);

        // Check if the tags are not null and not yet saved
//        if (task.getTags() != null && !task.getTags().isEmpty() && task.getTags().get(0).getId() == null) {
//            // Save the tags first
//            List<Tag> savedTags = tagRepository.saveAll(task.getTags());
//            task.setTags(savedTags);
//        }
//        validateTags(task);
        return taskRepository.save(task);
    }



//    private void taskCannotCreateInThePast(Task task) {
//
//        if (task.getCreatedAt() != null && task.getCreatedAt().isBefore(LocalDateTime.now()) ){
//            throw new IllegalArgumentException("The date is in the past !");
//        }
//    }


}
