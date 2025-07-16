package com.injob.back.repository;

import com.injob.back.model.FreeApplication;
import com.injob.back.model.JobApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreeApplicationRepository extends JpaRepository<FreeApplication,Long> {

    List<FreeApplication> findByEmail(String email);
}
