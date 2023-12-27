package com.taskflow.service.Impl;

import com.taskflow.dto.TagDTO;
import com.taskflow.dto.response.TagResponseDTO;
import com.taskflow.handlers.exception.ResourceNotFoundException;
import com.taskflow.mappers.TagMapper;
import com.taskflow.models.Tag;
import com.taskflow.repository.TagRepository;
import com.taskflow.service.TagService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TagServiceImpl  implements TagService {
    private final TagRepository tagRepository;

    private final TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }
    @Override
    public List<TagResponseDTO> getAllTags() {

            List<Tag> tags = tagRepository.findAll();
            return tags.stream()
                    .map(tagMapper::entityToDto)
                    .collect(Collectors.toList());
    }
    @Override
    public TagResponseDTO getTagById(Long tagId) {

            Optional<Tag> Tag = tagRepository.findById(tagId);
            if (Tag.isPresent()) {
                Tag tag = Tag.get();
                return tagMapper.entityToDto(tag);
            } else {
                throw new ResourceNotFoundException("Tag ID " + tagId+ " not found");
            }
    }

    @Override
    public boolean existsById(Long userId){
        boolean tagExist= tagRepository.existsById(userId);
        if(tagExist){
            return true;
        }
        return false;
    }
    @Override
    public TagResponseDTO createTag(TagDTO tagDTO) {

            Tag tag = tagMapper.dtoToEntity(tagDTO);
            Tag savedTag = tagRepository.save(tag);
            return tagMapper.entityToDto(savedTag);

    }
    @Override
    public TagResponseDTO updateTag(Long tagId, TagDTO tagDTO) {

            Optional<Tag> Tag = tagRepository.findById(tagId);
            if (Tag.isPresent()) {
                Tag tag = Tag.get();
                tag.setName(tagDTO.getName());
                Tag updatedTag = tagRepository.save(tag);
                return tagMapper.entityToDto(updatedTag);
            } else {
                throw new ResourceNotFoundException("Tag ID " + tagId+ " not found");
            }

    }
    @Override
    public void deleteTag(Long tagId) {

            Optional<Tag> Tag = tagRepository.findById(tagId);

            if (Tag.isPresent()) {
                tagRepository.deleteById(tagId);
            } else {
                throw new ResourceNotFoundException("Tag ID " + tagId+ " not found");
            }
    }
}
