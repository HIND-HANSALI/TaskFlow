package com.taskflow.mappers;

import com.taskflow.dto.TaskRequestDTO;
import com.taskflow.models.Task;

public class TaskMapper {
    public static Task mapTaskDtoToTask(TaskRequestDTO taskRequestDTO) {
        return Task.builder()
                .title(taskRequestDTO.getTitle())
                .description(taskRequestDTO.getDescription())
//                .isPassed(taskRequestDTO.getIsPassed())
                .startDate(taskRequestDTO.getStartDate())
                .endDate(taskRequestDTO.getEndDate())
                .taskStatus(taskRequestDTO.getTaskStatus())
                .taskAction(taskRequestDTO.getTaskAction())
                .tags(taskRequestDTO.getTags())
                //.assignedTo(taskRequestDTO.getAssignedTo().getId())
                //.createdBy(taskRequestDTO.getCreatedBy().getId())
                .build();
    }
}
