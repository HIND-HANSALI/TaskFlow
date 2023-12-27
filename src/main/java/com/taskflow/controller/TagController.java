package com.taskflow.controller;

import com.taskflow.dto.TagDTO;
import com.taskflow.dto.response.TagResponseDTO;
import com.taskflow.handlers.response.ResponseMessage;
import com.taskflow.service.TagService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<TagResponseDTO>> getAllTags() {
        List<TagResponseDTO> tags = tagService.getAllTags();

        if(tags.isEmpty()) {
            return ResponseMessage.notFound("Tag not found");
        }else {
            return ResponseMessage.ok(tags, "Success");
        }
    }
    @GetMapping("/{tagId}")
    public ResponseEntity<TagResponseDTO> getTagById(@PathVariable Long tagId) {
        TagResponseDTO tag = tagService.getTagById(tagId);
        if(tag == null) {
            return ResponseMessage.notFound("tag not found");
        }else {
            return ResponseMessage.ok(tag, "Success");
        }

    }
    @PostMapping
    public ResponseEntity<TagResponseDTO> createTag(@RequestBody @Valid TagDTO tagDTO) {
        TagResponseDTO createdTag = tagService.createTag(tagDTO);
        if(createdTag == null) {
            return ResponseMessage.badRequest("Tag not created");
        }else {
            return ResponseMessage.created(createdTag, "Tag created successfully");
        }

    }

    @PutMapping("/{tagId}")
    public ResponseEntity<TagResponseDTO> updateTag(@PathVariable Long tagId, @RequestBody @Valid TagDTO tagDTO) {
        TagResponseDTO updatedTag = tagService.updateTag(tagId, tagDTO);
        if(updatedTag  == null) {
            return ResponseMessage.badRequest("Tag not updated");
        }else {
            return ResponseMessage.created(updatedTag , "Tag updated successfully");
        }

    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long tagId) {
        TagResponseDTO tag =tagService.getTagById(tagId);
        if(tag== null) {
            return ResponseMessage.notFound("Tag not found");
        }else {
            tagService.deleteTag(tagId);
            return ResponseMessage.ok(null,"Tag deleted successfully");
        }

    }
}
