package com.injob.back.mapper;

import com.injob.back.dto.FreeApplicationDto;
import com.injob.back.model.FreeApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;

@Mapper(componentModel = "spring", imports = {Collections.class})
public interface FreeApplicationMapper {

    FreeApplication toFreeApplication(FreeApplicationDto dto);
    FreeApplicationDto toFreeApplicationDto(FreeApplication freeApplication);

}
