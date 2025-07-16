package com.injob.back.repository;

import com.injob.back.model.JobApply;
import com.injob.back.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplyRepository  extends JpaRepository<JobApply, Long> {
    boolean existsByJobOfferIdAndEmail(Long jobOfferId, String email);
    List<JobApply> findByEmail(String email);

    List<JobApply> findByJobOffer(JobOffer jobOffer);

}
