package com.taskflow.repository;

import com.taskflow.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
            List<Tag> findByTitleIn(List<Tag> names);
}
