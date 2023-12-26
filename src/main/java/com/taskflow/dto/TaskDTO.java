package com.taskflow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskflow.enums.TaskAction;
import com.taskflow.enums.TaskStatus;
import com.taskflow.models.Tag;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TaskDTO {
    @NotNull(message = "Title cannot be null")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Start date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Start date must be in the present or future")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "End date must be in the present or future")
    private LocalDate endDate;

//    @NotNull(message = "Completed status is required")
//    private Boolean isPassed;

    @NotNull(message = "At least one status is required")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @NotNull(message = "Created by user ID is required")
    private Long createdByUserId;

    @NotNull(message = "Assigned to user ID is required")
    private Long assignedToUserId;

    @NotEmpty(message = "At least one tag is required")
    private List<Long> tagIds;

//    @NotEmpty(message = "Tag is required")
//    @Size(min = 3, message = "At least three tags are required")
//    private List<Tag> tags;
}
