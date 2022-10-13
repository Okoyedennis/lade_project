package com.example.apply_for_school.services.impl;

import com.example.apply_for_school.dto.ApplyData;
import com.example.apply_for_school.dto.ApplyDto;
import com.example.apply_for_school.dto.ApplyResponse;
import com.example.apply_for_school.models.Apply;
import com.example.apply_for_school.repositories.ApplyRepository;
import com.example.apply_for_school.services.ApplyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplyServiceImpl implements ApplyService {

    private final ApplyRepository applyRepository;

    public ApplyServiceImpl(ApplyRepository applyRepository) {
        this.applyRepository = applyRepository;
    }

    @Override
    public ResponseEntity<Apply> applyAccount(ApplyDto applyDto) {

        Apply apply = Apply.builder()
                .firstName(applyDto.getFirstName())
                .lastName(applyDto.getLastName())
                .email(applyDto.getEmail())
                .stateOfOrigin(applyDto.getStateOfOrigin())
                .gender(applyDto.getGender())
                .dateOfBirth(applyDto.getDateOfBirth())
                .BVN(applyDto.getBVN())
                .build();

        applyRepository.save(apply);
        return ResponseEntity.ok().body(apply);
    }

    @Override
    public ResponseEntity<ApplyResponse> getAllPersons(int pageNo, int pageSize, String sortBy, String sortDir, String filterBy, String filterParam) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())  ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        if ("".equals(filterBy) && !"".equals(filterParam)) {
            Page<Apply> persons = applyRepository.findAllByFilterParam(pageable,filterParam.toLowerCase());
            return getApplyResponseEntity(persons);
        }else {
            switch (filterBy){
                case "firstName":{
                    Page<Apply> apply = applyRepository.findAllByApplyFirstNameContains(pageable,filterParam.toLowerCase());
                    return getApplyResponseEntity(apply);
                }
                case "lastName":{
                    Page<Apply> apply = applyRepository.findAllByApplyLastNameContains(pageable,filterParam.toLowerCase());
                    return getApplyResponseEntity(apply);
                }
                case "email":{
                    Page<Apply> apply = applyRepository.findAllByApplyEmailContains(pageable,filterParam.toLowerCase());
                    return getApplyResponseEntity(apply);
                }
                default:
                    Page<Apply> applies = applyRepository.findAll(pageable);
                    return getApplyResponseEntity(applies);
            }
        }
    }

    private ResponseEntity<ApplyResponse> getApplyResponseEntity(Page<Apply> applies){
        List<Apply> applyList = applies.getContent();
        List<ApplyData> personDataList = applyList.stream().map(this::convertToApplyData).collect(Collectors.toList());
        ApplyResponse applyResponse = ApplyResponse.builder()
                .content(personDataList)
                .pageNo(applies.getNumber())
                .totalPages(applies.getTotalPages())
                .totalElements(applies.getTotalElements())
                .last(applies.isLast())
                .pageSize(applies.getSize())
                .build();

        return new ResponseEntity<>(applyResponse, HttpStatus.OK);
    }

    private ApplyData convertToApplyData(Apply p){
        return ApplyData.builder()
                .id(p.getId())
                .email(p.getEmail())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .stateOfOrigin(p.getStateOfOrigin())
                .gender(p.getGender())
                .dateOfBirth(p.getDateOfBirth())
                .BVN(p.getBVN())
                .build();
    }
}
