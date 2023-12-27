package com.taskflow.repository;
import com.taskflow.models.User;
import com.taskflow.models.Userdetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(Long userId);
    Optional<Userdetails> findByEmail(String email);
}
