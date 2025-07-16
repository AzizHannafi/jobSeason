package com.injob.back.service.impl;

import com.injob.back.dto.UserSkillsDto;
import com.injob.back.mapper.UserSkillsMapper;
import com.injob.back.model.UserSkills;
import com.injob.back.repository.UserSkillsRepository;
import com.injob.back.utils.AuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSkillsService {
    @Autowired
    UserSkillsRepository userSkillsRepository;
    @Autowired
    UserSkillsMapper userSkillsMapper;
    // method to get user skills by user ID
    public List<UserSkills> getUserSkillsByUserEmail(String userEmail) {
        return userSkillsRepository.findJobOfferByUserEmail(userEmail);
    }
    // method to add user skills
    public UserSkills addUserSkills(UserSkillsDto userSkills, String email) {

        UserSkills skills = userSkillsMapper.toUserSkills(userSkills);
        skills.setUserEmail(email);
        return userSkillsRepository.save(skills);
    }
    // method to delete user skills by ID
    public void deleteUserSkillsById(Long id) {
        userSkillsRepository.deleteById(id);
    }
    // method to update user skills
    public UserSkills updateUserSkills(UserSkills userSkills) {
        return userSkillsRepository.save(userSkills);
    }
}
