package com.taskflow.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TagDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
}
