package com.taskflow.dto.response;


import com.taskflow.dto.TaskChangeDTO;
import com.taskflow.dto.TaskDTO;
import com.taskflow.enums.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;

    private Role role;
    private List<TaskDTO> createdTasks;
    private List<TaskDTO> assignedTasks;
    private List<TaskChangeDTO> taskChanges;
}

