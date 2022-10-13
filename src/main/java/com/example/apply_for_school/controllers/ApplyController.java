package com.example.apply_for_school.controllers;

import com.example.apply_for_school.dto.ApplyDto;
import com.example.apply_for_school.dto.ApplyResponse;
import com.example.apply_for_school.models.Apply;
import com.example.apply_for_school.services.ApplyService;
import com.example.apply_for_school.utils.PageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping("/apply")
    public ResponseEntity<Apply> applyAccount(@RequestBody ApplyDto applyDto){
        return applyService.applyAccount(applyDto);
    }

    @GetMapping("/getAllApplication")
    public ResponseEntity<ApplyResponse> getAllApplication(
            @RequestParam(value = "pageNo", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(value = "filterBy", defaultValue = PageConstants.DEFAULT_FILTER_BY_PARAMETER, required = false) String filterBy,
            @RequestParam(value = "filterParam", defaultValue = PageConstants.DEFAULT_FILTER_PARAMETER, required = false) String filterParam)
            throws ServletException {
        return applyService.getAllPersons(pageNo, pageSize, sortBy, sortDir, filterBy, filterParam);
    }
}
