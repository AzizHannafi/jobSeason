package com.injob.back.service.impl;

import com.injob.back.dto.JobOfferDto;
import com.injob.back.mapper.JobOfferMapper;
import com.injob.back.model.JobOffer;
import com.injob.back.repository.JobOfferRepository;
import com.injob.back.service.JobOfferService;
import com.injob.back.utils.AuthenticationUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final JobOfferMapper jobOfferMapper;


    public JobOfferDto addJobOffer(JobOfferDto jobOffer) {
        JobOffer givenJobOffer = jobOfferMapper.toJobOffer(jobOffer);
        givenJobOffer.setCompanyEmail(AuthenticationUtils.getEmailFromCurrentAuthentication());
        JobOffer newJobOffer  = jobOfferRepository.save(givenJobOffer);
        return jobOfferMapper.jobOfferToDto(newJobOffer);
    }

    public List<JobOfferDto> jobOfferList() {
        if(AuthenticationUtils.isAdmin()){
            return jobOfferRepository.findJobOfferByCompanyEmail(AuthenticationUtils.getEmailFromCurrentAuthentication()).stream().map(jobOfferMapper::jobOfferToDto).collect(Collectors.toList());
        }
        AuthenticationUtils.getCurrentAuthentication().getPrincipal();
        return jobOfferRepository.findAll().stream().map(jobOfferMapper::jobOfferToDto).collect(Collectors.toList());
    }

    public JobOfferDto updateJobOffer(Long jobId ,JobOfferDto jobOffer) {

        JobOffer existingJobOffer = jobOfferRepository.findById(jobId)
                .orElseThrow(() -> new EntityNotFoundException("JobOffer not found with ID: " + jobOffer.getId()));
        JobOfferDto existingJobOfferDto = jobOfferMapper.jobOfferToDto(existingJobOffer);
        // Update only the provided attributes in the request body
        if (jobOffer.getDatePublication() != null) {
            existingJobOfferDto.setDatePublication(jobOffer.getDatePublication());
        }
        if (jobOffer.getDescription() != null) {
            existingJobOfferDto.setDescription(jobOffer.getDescription());
        }
        if (jobOffer.getDateCloture() != null) {
            existingJobOfferDto.setDateCloture(jobOffer.getDateCloture());
        }

        if (jobOffer.getDateDebut() != null) {
            existingJobOfferDto.setDateDebut(jobOffer.getDateDebut());
        }
        if (jobOffer.getOfferType() != null) {
            existingJobOfferDto.setOfferType(jobOffer.getOfferType());
        }


        JobOffer updatedJobOffer = jobOfferRepository.save(jobOfferMapper.toJobOffer(existingJobOfferDto));

        return jobOfferMapper.jobOfferToDto(updatedJobOffer);
    }

    public void deleteJobOffer(Long jobId) {
        // Check if the JobOffer exists
        Optional<JobOffer> optionalJobOffer = jobOfferRepository.findById(jobId);
        if (optionalJobOffer.isEmpty()) {
            throw new EntityNotFoundException("JobOffer not found with ID: " + jobId);
        }

        // Delete the JobOffer
        jobOfferRepository.deleteById(jobId);
    }


    public JobOfferDto getJobOfferById(Long jobId) {
        Optional<JobOffer> optionalJobOffer = jobOfferRepository.findById(jobId);
        JobOffer jobOffer = optionalJobOffer.orElseThrow(() -> new IllegalArgumentException("JobOffer not found with ID: " + jobId));

        return jobOfferMapper.jobOfferToDto(jobOffer);
    }
}
