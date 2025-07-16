package com.injob.back.service.impl;

import com.injob.back.dto.InterviewDto;
import com.injob.back.dto.PostInterviewDto;
import com.injob.back.enums.InterviewStatusEnum;
import com.injob.back.mapper.InterviewMapper;
import com.injob.back.model.Interview;
import com.injob.back.model.JobApply;
import com.injob.back.repository.InterviewRepository;
import com.injob.back.repository.JobApplyRepository;
import com.injob.back.service.InterviewService;
import com.injob.back.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl  implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final JobApplyRepository jobApplyRepository; // Assuming you have a repository for JobApply

    private final InterviewMapper interviewMapper;



    public InterviewDto addInterview(Long jobApplyId, PostInterviewDto interviewDto) {
        Optional<JobApply> optionalJobApply = jobApplyRepository.findById(jobApplyId);
        if (optionalJobApply.isPresent()) {
            JobApply jobApply = optionalJobApply.get();

            // Assuming your InterviewDto has the necessary information
            Interview interview = interviewMapper.postToInterview(interviewDto);
            interview.setEmail(jobApply.getEmail());
            interview.setInterviewStatus(InterviewStatusEnum.PLANNED);

            // Set the JobApply in the Interview
            interview.setJobApply(jobApply);
            Interview savedInterview = interviewRepository.save(interview);

            return interviewMapper.toInterviewDto(savedInterview);
        } else {
            throw new IllegalArgumentException("JobApply with ID " + jobApplyId + " not found.");
        }
    }

    public InterviewDto getInterviewById(Long interviewId) {
        Optional<Interview> optionalInterview = interviewRepository.findById(interviewId);
        return optionalInterview.map(interviewMapper::toInterviewDto)
                .orElseThrow(() -> new IllegalArgumentException("Interview with ID " + interviewId + " not found."));
    }

    public InterviewDto updateInterviewStatus(Long interviewId, InterviewStatusEnum newStatus) {
        Optional<Interview> optionalInterview = interviewRepository.findById(interviewId);
        if (optionalInterview.isPresent()) {
            Interview existingInterview = optionalInterview.get();

            // Update the interview status
            existingInterview.setInterviewStatus(newStatus);

            Interview updatedInterview = interviewRepository.save(existingInterview);
            return interviewMapper.toInterviewDto(updatedInterview);
        } else {
            throw new IllegalArgumentException("Interview with ID " + interviewId + " not found.");
        }
    }

    public void deleteInterview(Long interviewId) {
        if (interviewRepository.existsById(interviewId)) {
            interviewRepository.deleteById(interviewId);
        } else {
            throw new IllegalArgumentException("Interview with ID " + interviewId + " not found.");
        }
    }

    public List<InterviewDto> getAllInterviews() {
        List<Interview> interviews = new ArrayList<>();
        if (AuthenticationUtils.isAdmin()) {
            interviews = interviewRepository.findAll();
        } else {
            String userEmail = AuthenticationUtils.getEmailFromCurrentAuthentication();
            interviews = interviewRepository.findByEmail(userEmail);
        }
        return interviews.stream()
                .map(interviewMapper::toInterviewDto)
                .collect(Collectors.toList());
    }
}
