package com.taskflow.mappers;

import com.taskflow.dto.TaskChangeDTO;
import com.taskflow.dto.response.TaskChangeResponseDTO;
import com.taskflow.models.TaskChange;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TaskMapper.class})
public interface TaskChangeMapper {
    TaskChangeMapper INSTANCE = Mappers.getMapper(TaskChangeMapper.class);

    @Mapping(target = "user", source = "user")
    @Mapping(target = "task", source = "task")

    TaskChangeResponseDTO entityToDto(TaskChange taskChange );

    TaskChange dtoToEntity(TaskChangeDTO taskChangeDTO);
}
