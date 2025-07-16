package com.injob.back.repository;

import com.injob.back.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
  List<JobOffer> findJobOfferByCompanyEmail(String email);
}
