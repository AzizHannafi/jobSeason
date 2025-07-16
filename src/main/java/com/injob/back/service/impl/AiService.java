package com.injob.back.service.impl;

import com.injob.back.dto.JobOfferDto;
import com.injob.back.model.UserSkills;
import com.injob.back.utils.AuthenticationUtils;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AiService {
  @Autowired
  private JobOfferServiceImpl jobOfferSrv;
  @Autowired
  private ChatModel chatModel;
  @Autowired
  private UserSkillsService userSkillsService;

  public String generateCoverLetter(String jobId) {
    JobOfferDto jobOffer = jobOfferSrv.getJobOfferById(Long.parseLong(jobId));
    if (jobOffer == null) {
      throw new RuntimeException("Job offer not found");
    }
    List<UserSkills> userSkillsByUserEmail = userSkillsService.getUserSkillsByUserEmail(AuthenticationUtils.getEmailFromCurrentAuthentication());
    String prompt = """
        You are a professional cover letter writer.
        
        Use ONLY the two sections below to write a complete, professional, and ready-to-send cover letter. 
        Do NOT include any placeholders like [Your Name], [Company Name], [Today's Date] or any like [Nom du candidat omis],[*]  etc.
        Do NOT assume or add any information that is not explicitly included.
        
        SECTION 1: Job Offer Description
        ---
        %s
        ---
        
        SECTION 2: Candidate's Skills
        ---
        %s
        ---
        
        Instructions:
        - Use only the information from the two sections above.
        - Write the letter in a formal and structured format.
        - The letter should be complete and require no further edits.
        - If company name or candidate name is not provided, just omit them — do not insert fake or placeholder names.
        """.formatted(jobOffer.getDescription(), userSkillsByUserEmail.toString());

    String response = this.chatModel.call(prompt);

    return response;
  }
}
