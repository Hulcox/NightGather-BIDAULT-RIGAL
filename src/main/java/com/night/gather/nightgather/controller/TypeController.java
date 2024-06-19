package com.night.gather.nightgather.controller;

import com.night.gather.nightgather.dto.TypeDto;
import com.night.gather.nightgather.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping
    public ResponseEntity<List<TypeDto>> getAllTypes() {
        return ResponseEntity.ok(typeService.getAllTypes());
    }
}
