package com.injob.back.service.impl;

import com.injob.back.dto.FreeApplicationDto;
import com.injob.back.enums.StatusEnum;
import com.injob.back.mapper.FreeApplicationMapper;
import com.injob.back.model.FreeApplication;
import com.injob.back.repository.FreeApplicationRepository;
import com.injob.back.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FreeApplicationServiceImpl {

    private final FreeApplicationRepository freeApplicationRepository;
    private final FreeApplicationMapper freeApplicationMapper;


    public FreeApplicationDto addFreeApplication(FreeApplicationDto freeApplicationDto) {
        FreeApplication freeApplication = freeApplicationMapper.toFreeApplication(freeApplicationDto);

        freeApplication.setEmail(AuthenticationUtils.getEmailFromCurrentAuthentication());
        freeApplication.setStatus(StatusEnum.PENDING);
        FreeApplication savedFreeApplication = freeApplicationRepository.save(freeApplication);

        return freeApplicationMapper.toFreeApplicationDto(savedFreeApplication);
    }

    public FreeApplicationDto updateFreeApplicationStatus(Long freeApplicationId, StatusEnum newStatus) {
        Optional<FreeApplication> optionalFreeApplication = freeApplicationRepository.findById(freeApplicationId);

        if (optionalFreeApplication.isPresent()) {
            FreeApplication freeApplication = optionalFreeApplication.get();
            freeApplication.setStatus(newStatus);

            FreeApplication updatedFreeApplication = freeApplicationRepository.save(freeApplication);

            return freeApplicationMapper.toFreeApplicationDto(updatedFreeApplication);
        } else {
            throw new IllegalArgumentException("FreeApplication with ID " + freeApplicationId + " not found.");
        }
    }

    public void deleteFreeApplication(Long freeApplicationId) {
        // Check if the FreeApplication exists
        if (freeApplicationRepository.existsById(freeApplicationId)) {
            // Delete the FreeApplication by ID
            freeApplicationRepository.deleteById(freeApplicationId);
        } else {
            throw new IllegalArgumentException("FreeApplication with ID " + freeApplicationId + " not found.");
        }
    }

    public List<FreeApplicationDto> getAllFreeApplications() {
        List<FreeApplication> freeApplications = new ArrayList<>();
        if (AuthenticationUtils.isAdmin()) {
            freeApplications = freeApplicationRepository.findAll();
        } else {
            String userEmail = AuthenticationUtils.getEmailFromCurrentAuthentication();
            freeApplications = freeApplicationRepository.findByEmail(userEmail);
        }
        return freeApplications.stream()
                .map(freeApplicationMapper::toFreeApplicationDto)
                .collect(Collectors.toList());
    }

}
