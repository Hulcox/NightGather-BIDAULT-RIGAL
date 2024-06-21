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
        User userSaved = getUserByEmail(userDto);
        byte[] salt = Password.fromHex(userSaved.getSalt());
        String hashUserSupplied = Password.createHash(userDto.getPassword().toCharArray(), salt);
        if (hashUserSupplied.equals(userSaved.getPassword())) {
            String jwt = AuthenticationWithJwt.create(userSaved);
            return Map.of("token", jwt);
        }

        log.error("🔴 Mot de passe invalide");
        return null;
    }

    public Map<String, Object> signUp(UserDto userDto) {
        User userSaved = getUserByEmail(userDto);
        if (userSaved != null) {
            log.error("🔴 L'utilisateur existe déjà");
            return null;
        }
        User user = userMapper.toEntity(userDto);
        byte[] salt = Password.createSalt();
        String hash = Password.createHash(userDto.getPassword().toCharArray(), salt);
        user.setSalt(Password.toHex(salt));
        user.setPassword(hash);
        userRepository.save(user);
        String jwt = AuthenticationWithJwt.create(user);
        return Map.of("token", jwt);
    }

    public User getUserByEmail(UserDto userDto) {
        return userRepository.findByEmail(userDto.getEmail()).orElse(null);
    }
}
