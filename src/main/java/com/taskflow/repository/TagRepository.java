package com.taskflow.repository;

import com.taskflow.dto.TagDTO;
import com.taskflow.dto.response.TagResponseDTO;
import com.taskflow.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByNameIn(List<Tag> names);

}
