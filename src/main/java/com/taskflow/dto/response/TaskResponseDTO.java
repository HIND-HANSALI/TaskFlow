package com.taskflow.dto.response;


import com.taskflow.dto.TagDTO;
import com.taskflow.dto.TaskChangeDTO;
import com.taskflow.dto.UserDTO;
import com.taskflow.enums.TaskStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;
    private UserDTO createdBy;
    private UserDTO assignedTo;
    private TaskStatus taskStatus;
    private List<TagDTO> tags;
    private List<TaskChangeDTO> taskChanges;
}
