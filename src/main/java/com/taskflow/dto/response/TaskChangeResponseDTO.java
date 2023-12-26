package com.taskflow.dto.response;

import com.taskflow.dto.TaskDTO;
import com.taskflow.dto.UserDTO;
import com.taskflow.enums.StatusRequest;
import com.taskflow.enums.TokenType;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskChangeResponseDTO {
    private Long id;
    private LocalDateTime demandDate;
    private StatusRequest status;
    private TokenType type;
    private UserDTO user;
    private TaskDTO task;
}
