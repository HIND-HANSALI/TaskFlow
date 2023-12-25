package com.taskflow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskflow.enums.TaskStatus;
import com.taskflow.models.Tag;
import com.taskflow.models.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record TaskDto(

        @NotNull(message = "Title cannot be null")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Start date cannot be null")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,

        @NotNull(message = "End date cannot be null")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,


        TaskStatus taskStatus,

//        @Size(min = 1, message = "At least 1 tag is required")
        List<Tag> tags

//        boolean isPassed

) {
    public Task toTask() {
//        List<Tag> tagEntities = tags.stream()
//                .map(tagTitle -> new Tag(tagTitle))
//                .collect(Collectors.toList());
        return Task.builder()
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .taskStatus(taskStatus)

                .tags(tags)
                .build();
    }
}
