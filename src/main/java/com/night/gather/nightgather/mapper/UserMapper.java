package com.night.gather.nightgather.mapper;

import com.night.gather.nightgather.dto.MemberDto;
import com.night.gather.nightgather.dto.UserDto;
import com.night.gather.nightgather.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RateMapper.class})
public interface UserMapper {

    UserDto toDto(User user);

    @Named("toDtoInPage")
    @Mapping(target = "rates", ignore = true)
    UserDto toDtoInPage(User user);

    User toEntity(UserDto userDto);

    List<UserDto> toDtos(List<User> users);

    List<User> toEntities(List<UserDto> usersDto);

    List<MemberDto> toMemberDtos(List<User> users);
}
