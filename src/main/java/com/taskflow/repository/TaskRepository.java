package com.taskflow.repository;

import com.taskflow.models.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.taskStatus = 'Done' WHERE t.id = :taskId AND t.endDate >= :currentDate AND t.taskStatus  = 'InProgress'")
    void updateTaskStatusToDone(@Param("taskId") Long taskId, @Param("currentDate") LocalDate currentDate);
}
