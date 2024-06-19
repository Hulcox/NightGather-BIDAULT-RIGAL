package com.night.gather.nightgather.service;

import com.night.gather.nightgather.dto.TypeDto;
import com.night.gather.nightgather.mapper.TypeMapper;
import com.night.gather.nightgather.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TypeService {

    @Autowired
    private final TypeRepository typeRepository;

    private final TypeMapper typeMapper;

    public List<TypeDto> getAllTypes(){
        return typeMapper.toDtos(typeRepository.findAll());
    }
}
