package com.injob.back.mapper;

import com.injob.back.dto.UserSkillsDto;
import com.injob.back.model.UserSkills;
import org.mapstruct.Mapper;

import java.util.Collections;

@Mapper(componentModel = "spring", imports = {Collections.class})
public interface UserSkillsMapper {

    UserSkills toUserSkills(UserSkillsDto dto);
    UserSkillsDto toUserSkillsDto(UserSkills UserSkills);

}
