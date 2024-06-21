package com.night.gather.nightgather.service;

import com.night.gather.nightgather.dto.UserDto;
import com.night.gather.nightgather.entity.User;
import com.night.gather.nightgather.mapper.UserMapper;
import com.night.gather.nightgather.repository.UserRepository;
import com.night.gather.nightgather.security.AuthenticationWithJwt;
import com.night.gather.nightgather.security.Password;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public List<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDtoInPage).getContent();
    }

    public UserDto getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(userMapper::toDto).orElse(null);
    }

    public Map<String, Object> signIn(UserDto userDto) {
        try {
            User userSaved = userRepository.findByEmail(userDto.getEmail()).orElse(null);
            byte[] salt = Password.fromHex(userSaved.getSalt());
            String hashUserSupplied = Password.createHash(userDto.getPassword().toCharArray(), salt);
            if (hashUserSupplied.equals(userSaved.getPassword())) {
                String jwt = AuthenticationWithJwt.create(userSaved);
                return Map.of("token", jwt);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.error("🔴 Mot de passe invalide");
        return null;
    }
}
