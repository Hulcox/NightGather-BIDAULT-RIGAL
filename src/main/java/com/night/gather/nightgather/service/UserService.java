package com.night.gather.nightgather.service;

import com.night.gather.nightgather.dto.UserDto;
import com.night.gather.nightgather.mapper.UserMapper;
import com.night.gather.nightgather.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> findAll(){
        return userMapper.toDtos(userRepository.findAll());
    }
}
