package com.night.gather.nightgather.controller;

import com.night.gather.nightgather.dto.UserDto;
import com.night.gather.nightgather.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.night.gather.nightgather.security.AuthenticationWithJwt.verifyJwt;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size, @RequestHeader("Authorization") final Optional<String> token) {
        if (token.isPresent() && verifyJwt(token.get()) != null) {
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(userService.getAllUsers(pageable));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/signIn")
    public ResponseEntity<Object> signIn(@RequestBody UserDto userDto) {
        Map<String,Object> signedIn = userService.signIn(userDto);
        if (signedIn != null) {
            return ResponseEntity.ok(signedIn);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(@RequestBody UserDto userDto) {
        Map<String,Object> signedUp = userService.signUp(userDto);
        if (signedUp != null) {
            return ResponseEntity.ok(signedUp);
        }
        return ResponseEntity.badRequest().build();
    }
}
