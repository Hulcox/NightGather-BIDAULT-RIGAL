package com.night.gather.nightgather.controller;

import com.night.gather.nightgather.dto.UserDto;
import com.night.gather.nightgather.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userservice;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll()
    {
        return ResponseEntity.ok(userservice.findAll());
    }
}
