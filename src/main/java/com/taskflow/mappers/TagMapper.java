package com.taskflow.mappers;
import com.taskflow.dto.TagDTO;
import com.taskflow.dto.response.TagResponseDTO;
import com.taskflow.models.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
    TagResponseDTO entityToDto(Tag tag);
    Tag dtoToEntity(TagDTO tagDTO);

}
