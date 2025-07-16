package com.injob.back.controller;

import com.injob.back.dto.JobOfferDto;
import com.injob.back.security.roles.hasAdminRole;
import com.injob.back.service.impl.JobOfferServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/joboffers")
@SecurityRequirement(name = "basicAuth")
@Tag(name = "JobOffer controller ", description = "JobOffer controller  Api description")
public class JobOfferController {
    private final JobOfferServiceImpl jobOfferService;


    @PostMapping
    @hasAdminRole
    public ResponseEntity<JobOfferDto> addJobOffer(@RequestBody JobOfferDto jobOffer) {
        JobOfferDto addedJobOffer = jobOfferService.addJobOffer(jobOffer);

        return new ResponseEntity<>(addedJobOffer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JobOfferDto> > JobOffers() {

        List<JobOfferDto>  jobOfferList = jobOfferService.jobOfferList();

        return new ResponseEntity<>(jobOfferList, HttpStatus.OK);
    }

    @PutMapping("/{jobId}")
    @hasAdminRole

    public ResponseEntity<JobOfferDto> updateJobOffer(
            @PathVariable Long jobId,
            @RequestBody JobOfferDto jobOffer) {
        return   new ResponseEntity<>(jobOfferService.updateJobOffer(jobId,jobOffer),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{jobId}")
    @hasAdminRole
    public ResponseEntity<Void> deleteJobOffer(@PathVariable Long jobId) {
        jobOfferService.deleteJobOffer(jobId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{jobId}")
    public ResponseEntity<JobOfferDto> getJobOfferById(@PathVariable Long jobId) {
        JobOfferDto jobOfferDto = jobOfferService.getJobOfferById(jobId);
        return ResponseEntity.ok(jobOfferDto);
    }
}
