package com.taskflow.mappers;

import com.taskflow.dto.TaskDTO;
import com.taskflow.dto.response.TaskResponseDTO;
import com.taskflow.models.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    @Mapping(target = "createdBy", source = "createdBy")
    @Mapping(target = "assignedTo", source = "assignedTo")
    TaskResponseDTO entityToDto(Task task);
    Task dtoToEntity(TaskDTO taskDTO);


























//    public static Task mapTaskDtoToTask(TaskRequestDTO taskRequestDTO) {
//        return Task.builder()
//                .title(taskRequestDTO.getTitle())
//                .description(taskRequestDTO.getDescription())
////                .isPassed(taskRequestDTO.getIsPassed())
//                .startDate(taskRequestDTO.getStartDate())
//                .endDate(taskRequestDTO.getEndDate())
//                .taskStatus(taskRequestDTO.getTaskStatus())
//                .taskAction(taskRequestDTO.getTaskAction())
//                .tags(taskRequestDTO.getTags())
//
//                .build();
//    }
}
