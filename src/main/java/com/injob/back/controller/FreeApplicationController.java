package com.injob.back.controller;

import com.injob.back.dto.FreeApplicationDto;
import com.injob.back.enums.StatusEnum;
import com.injob.back.security.roles.hasAdminRole;
import com.injob.back.security.roles.hasUserRole;
import com.injob.back.service.impl.FreeApplicationServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/freeApplications")
@AllArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class FreeApplicationController {
    private final FreeApplicationServiceImpl freeApplicationService;

    @hasUserRole
    @PostMapping("/add")
    public ResponseEntity<FreeApplicationDto> addFreeApplication(@RequestBody FreeApplicationDto freeApplicationDto) {
        FreeApplicationDto addedFreeApplication = freeApplicationService.addFreeApplication(freeApplicationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedFreeApplication);
    }

    @hasAdminRole
    @PatchMapping("/updateStatus/{freeApplicationId}")
    public ResponseEntity<FreeApplicationDto> updateFreeApplicationStatus(
            @PathVariable Long freeApplicationId,
            @RequestParam StatusEnum newStatus
    ) {
        FreeApplicationDto updatedFreeApplication = freeApplicationService.updateFreeApplicationStatus(freeApplicationId, newStatus);
        return ResponseEntity.ok(updatedFreeApplication);
    }

    @hasAdminRole
    @DeleteMapping("/delete/{freeApplicationId}")
    public ResponseEntity<Void> deleteFreeApplication(@PathVariable Long freeApplicationId) {
        freeApplicationService.deleteFreeApplication(freeApplicationId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FreeApplicationDto>> getAllFreeApplications() {
        List<FreeApplicationDto> freeApplications = freeApplicationService.getAllFreeApplications();
        return ResponseEntity.ok(freeApplications);
    }
}
