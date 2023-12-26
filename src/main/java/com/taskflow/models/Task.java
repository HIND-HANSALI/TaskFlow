package com.taskflow.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskflow.enums.TaskAction;
import com.taskflow.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Creation date is required")
    @FutureOrPresent(message = "Creation date must be in the present or the future")
    private LocalDate startDate;

    @NotNull(message = "Date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Enumerated(EnumType.STRING)
    private TaskAction taskAction;

    @Builder.Default
    private boolean isPassed= false;

    @JsonBackReference
    @ManyToOne
    private User user;

    @ManyToMany
    private List<Tag> tags ;

//    @OneToOne
//    private TaskChangeRequest taskChangeRequest;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt;


}
