package com.night.gather.nightgather.service;

import com.night.gather.nightgather.dto.UserDto;
import com.night.gather.nightgather.entity.User;
import com.night.gather.nightgather.mapper.UserMapper;
import com.night.gather.nightgather.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public List<UserDto> getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable).map(userMapper::toDtoInPage).getContent();
    }

    public UserDto getUserById(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(userMapper::toDto).orElse(null);
    }
}
