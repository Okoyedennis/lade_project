package com.example.apply_for_school.services;

import com.example.apply_for_school.dto.ApplyDto;
import com.example.apply_for_school.dto.ApplyResponse;
import com.example.apply_for_school.models.Apply;
import org.springframework.http.ResponseEntity;

public interface ApplyService {

    ResponseEntity<Apply> applyAccount(ApplyDto applyDto);

    ResponseEntity<ApplyResponse> getAllPersons(int pageNo, int pageSize, String sortBy, String sortDir, String filterBy, String filterParam);
}
