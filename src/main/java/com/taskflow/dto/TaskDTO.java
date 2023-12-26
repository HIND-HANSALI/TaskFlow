package com.taskflow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskflow.enums.TaskAction;
import com.taskflow.enums.TaskStatus;
import com.taskflow.models.Tag;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequestDTO {
    @NotNull(message = "Title cannot be null")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Start date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

//    @NotNull(message = "Completed status is required")
//    private Boolean isPassed;

    @NotNull(message = "At least one status is required")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Enumerated(EnumType.STRING)
    private TaskAction taskAction;

    @NotEmpty(message = "Tag is required")
    @Size(min = 3, message = "At least three tags are required")
    private List<Tag> tags;
}
