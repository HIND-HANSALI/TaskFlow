package com.taskflow.models;

import com.taskflow.enums.StatusRequest;
import com.taskflow.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Builder
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TaskChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "demand_date")
    private LocalDateTime demandDate;

    @Enumerated(EnumType.STRING)
    private StatusRequest status;

    @Enumerated(EnumType.STRING)
    private TokenType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
