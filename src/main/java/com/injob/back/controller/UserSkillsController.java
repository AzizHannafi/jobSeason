package com.injob.back.controller;

import com.injob.back.dto.UserSkillsDto;
import com.injob.back.model.UserSkills;
import com.injob.back.service.impl.UserSkillsService;
import com.injob.back.utils.AuthenticationUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/skills")
@SecurityRequirement(name = "basicAuth")
@Tag(name = "UserSkills controller ", description = "UserSkills controller  Api description")
public class UserSkillsController {
    @Autowired
    private UserSkillsService userSkillsService;

    @PostMapping("/add")
    public ResponseEntity<?> addUserSkills(@RequestBody @Valid List<UserSkillsDto> userSkills) {
      String email = AuthenticationUtils.getEmailFromCurrentAuthentication();

      if (email == null) {
        throw new AccessDeniedException("Vous n'êtes pas connecté");
      }

      for (UserSkillsDto userSkill : userSkills) {
        boolean skillExists = userSkillsService.getUserSkillsByUserEmail(email)
            .stream()
            .anyMatch(s -> s.getSkillName().equals(userSkill.getSkillName()));

        if (skillExists) {
          continue;
        }

        userSkillsService.addUserSkills(userSkill, email);
      }

      return ResponseEntity.status(HttpStatus.CREATED).body(userSkills);
    }
    @PostMapping("/update")
    public ResponseEntity<UserSkills> updateUserSkills(@RequestBody UserSkills userSkills) {
        UserSkills updatedUserSkills = userSkillsService.updateUserSkills(userSkills);
        return ResponseEntity.ok(updatedUserSkills);
    }
    @PostMapping("/delete")
    public ResponseEntity<Void> deleteUserSkills(@RequestBody Long id) {
        userSkillsService.deleteUserSkillsById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/getAll")
    public ResponseEntity<List<UserSkills>> getAllUserSkills() {
        List<UserSkills> userSkillsList = userSkillsService.getUserSkillsByUserEmail(AuthenticationUtils.getEmailFromCurrentAuthentication());
        return ResponseEntity.ok(userSkillsList);
    }

}
