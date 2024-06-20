package com.night.gather.nightgather.mapper;

import com.night.gather.nightgather.dto.MemberDto;
import com.night.gather.nightgather.dto.UserDto;
import com.night.gather.nightgather.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    List<UserDto> toDtos(List<User> users);

    List<User> toEntities(List<UserDto> usersDto);

    List<MemberDto> toMemberDtos(List<User> users);
}
