package com.example.apply_for_school.dto;

import com.example.apply_for_school.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplyDto {
    private String firstName;
    private String lastName;
    private String email;
    private String stateOfOrigin;
    private Gender gender;
    private LocalDate dateOfBirth;
    private Long BVN;
}
