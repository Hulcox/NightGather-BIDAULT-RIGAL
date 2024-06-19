package com.night.gather.nightgather.mapper;

import com.night.gather.nightgather.dto.RateDto;
import com.night.gather.nightgather.entity.Rate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface RateMapper {
    RateDto toDto(Rate rate);
    Rate toEntity(RateDto rateDto);
    List<RateDto> toDtos(List<Rate> rates);
    List<Rate> toEntities(List<RateDto> ratesDto);
}
