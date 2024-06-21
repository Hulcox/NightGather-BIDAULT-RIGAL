package com.night.gather.nightgather.mapper;


import com.night.gather.nightgather.dto.TypeDto;
import com.night.gather.nightgather.entity.Type;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeMapper {
    TypeDto toDto(Type type);
    Type toEntity(TypeDto typeDto);
    List<TypeDto> toDtos(List<Type> types);
    List<Type> toEntities(List<TypeDto> typesDto);
}
