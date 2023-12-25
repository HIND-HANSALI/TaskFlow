package com.taskflow.service.Impl;

import com.taskflow.handlers.exception.OperationException;
import com.taskflow.models.Tag;
import com.taskflow.models.Task;
import com.taskflow.repository.TagRepository;
import com.taskflow.repository.TaskRepository;
import com.taskflow.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;

    public TaskServiceImpl(TaskRepository taskRepository,TagRepository tagRepository) {
        this.taskRepository = taskRepository;
       this.tagRepository=tagRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long Id) {
        return null;
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
        validateTags(task);
        return taskRepository.save(task);
    }
    private void validateTags(Task task) {

//        if (task.getTags() == null || task.getTags().size() < 2) {
//            throw new IllegalArgumentException("At least 2 tags is required !");
//        }

        //here I want to check if tag already exists in database to skip saving it, else to save it!
        List<Tag> tagNames = task.getTags();
//        if (tagNames == null) {
//            tagNames = new ArrayList<>();
//        }

        List<Tag> existingTags = tagRepository.findByTitleIn(tagNames);
        if(existingTags == null || existingTags.isEmpty()) existingTags = new ArrayList<>();
        for (Tag tagName : tagNames) {
            if (!(existingTags.contains(tagName)))
                existingTags.add(tagRepository.save(tagName));
        }

        task.setTags(existingTags);
    }

//    private void taskCannotCreateInThePast(Task task) {
//
//        if (task.getCreatedAt() != null && task.getCreatedAt().isBefore(LocalDateTime.now()) ){
//            throw new IllegalArgumentException("The date is in the past !");
//        }
//    }
    private void validateTask(Task task) {
        // Check if the start date is before the end date
        if (task.getStartDate().isAfter(task.getEndDate())) {
            throw new OperationException("Start date must be before end date");
        }

        // Check if the task has at least 3 tags
//        if (task.getTags() == null || task.getTags().size() < 3) {
//            throw new OperationException("Task must have at least 3 tags");
//        }

    }

}
