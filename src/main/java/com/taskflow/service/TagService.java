package com.taskflow.service;

import com.taskflow.dto.TagDTO;
import com.taskflow.dto.response.TagResponseDTO;

import java.util.List;

public interface TagService {
    List<TagResponseDTO> getAllTags();
    boolean existsById(Long userId);
    TagResponseDTO createTag(TagDTO tagDTO);
    TagResponseDTO updateTag(Long tagId, TagDTO tagDTO);
    void deleteTag(Long tagId);
    TagResponseDTO getTagById(Long tagId);

}
