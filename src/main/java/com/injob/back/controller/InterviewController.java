package com.injob.back.controller;

import com.injob.back.dto.InterviewDto;
import com.injob.back.dto.PostInterviewDto;
import com.injob.back.enums.InterviewStatusEnum;
import com.injob.back.security.roles.hasAdminRole;
import com.injob.back.service.InterviewService;
import com.injob.back.service.impl.InterviewServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviews")
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class InterviewController {
    private final InterviewServiceImpl interviewService;

    @PostMapping("/add/{jobApplyId}")
    @hasAdminRole
    public ResponseEntity<InterviewDto> addInterview(@PathVariable Long jobApplyId,
                                                     @RequestBody PostInterviewDto interviewDto) {
        try {
            InterviewDto addedInterview = interviewService.addInterview(jobApplyId, interviewDto);
            return new ResponseEntity<>(addedInterview, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{interviewId}")
    public ResponseEntity<InterviewDto> getInterviewById(@PathVariable Long interviewId) {
        InterviewDto interviewDto = interviewService.getInterviewById(interviewId);
        return ResponseEntity.ok(interviewDto);
    }

    @PatchMapping("/update-status/{interviewId}")
    @hasAdminRole
    public ResponseEntity<InterviewDto> updateInterviewStatus(
            @PathVariable Long interviewId,
            @RequestParam InterviewStatusEnum newStatus
    ) {
        InterviewDto updatedInterview = interviewService.updateInterviewStatus(interviewId, newStatus);
        return ResponseEntity.ok(updatedInterview);
    }

    @DeleteMapping("/delete/{interviewId}")
    @hasAdminRole
    public ResponseEntity<Void> deleteInterview(@PathVariable Long interviewId) {
        interviewService.deleteInterview(interviewId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<InterviewDto>> getAllInterviews() {
        List<InterviewDto> interviews = interviewService.getAllInterviews();
        return ResponseEntity.ok(interviews);
    }
}
