package com.night.gather.nightgather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String lastname;
    private String firstname;
    private String username;
    private int age;
//    private List<RateDto> rates;
}

