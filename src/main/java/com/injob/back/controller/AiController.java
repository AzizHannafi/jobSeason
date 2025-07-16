package com.injob.back.controller;

import com.injob.back.dto.UserSkillsDto;
import com.injob.back.model.UserSkills;
import com.injob.back.service.impl.AiService;
import com.injob.back.service.impl.UserSkillsService;
import com.injob.back.utils.AuthenticationUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Tag(name = "Ai controller ", description = "AI  Api description")
@SecurityRequirement(name = "basicAuth")
public class AiController {
    @Autowired
  private ChatModel chatModel;
private final AiService aiService;
  @PostMapping("/chat")
  public ResponseEntity<String> chatWithOpenRouter(@RequestBody String chatRequest) {

    try {
      String response = this.chatModel.call(chatRequest);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Sorry, an error occurred while communicating with the AI service.");
    }
    }  @PostMapping("/chat/job/{jobId}")
  public ResponseEntity<String> generateCoverLetter(@PathVariable String jobId) {

    try {

      String response = aiService.generateCoverLetter(jobId);

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Sorry, an error occurred while communicating with the AI service.");
    }
    }
}

