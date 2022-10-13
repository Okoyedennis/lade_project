package com.example.apply_for_school.dto;

import com.example.apply_for_school.enums.Gender;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyData {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String stateOfOrigin;
    private Gender gender;
    private LocalDate dateOfBirth;
    private Long BVN;
}
