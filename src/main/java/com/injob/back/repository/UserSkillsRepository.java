package com.injob.back.repository;

import com.injob.back.model.UserSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSkillsRepository extends JpaRepository<UserSkills, Long> {
    List<UserSkills> findJobOfferByUserEmail(String userEmail);
}
