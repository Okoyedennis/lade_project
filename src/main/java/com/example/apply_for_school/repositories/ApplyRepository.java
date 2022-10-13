package com.example.apply_for_school.repositories;

import com.example.apply_for_school.models.Apply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {
    Optional<Apply> findApplyByEmail(String email);

    @Query("SELECT a FROM Apply a WHERE " + "LOWER(" + "CONCAT(a.Id, a.firstName, a.lastName, a.email))" +  "LIKE %?1%")
    Page<Apply> findAllByFilterParam (Pageable pageable, String filterParam);

    @Query("SELECT a FROM Apply a WHERE " + "LOWER(" + "a.firstName)" + "LIKE %?1%")
    Page<Apply> findAllByApplyFirstNameContains (Pageable pageable, String firstName);

    @Query("SELECT a FROM Apply a WHERE " + "LOWER(" + "a.lastName)" + "LIKE %?1%")
    Page<Apply> findAllByApplyLastNameContains (Pageable pageable, String lastName);

    @Query("SELECT a FROM Apply a WHERE " + "LOWER(" + "a.lastName)" + "LIKE %?1%")
    Page<Apply> findAllByApplyEmailContains (Pageable pageable, String email);
}
