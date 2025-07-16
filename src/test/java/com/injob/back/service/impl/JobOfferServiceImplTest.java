package com.injob.back.service.impl;

import com.injob.back.dto.JobOfferDto;
import com.injob.back.mapper.JobOfferMapper;
import com.injob.back.model.JobOffer;
import com.injob.back.repository.JobOfferRepository;
import com.injob.back.utils.AuthenticationUtils;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.testng.AssertJUnit.assertEquals;

public class JobOfferServiceImplTest {
    @Mock
    JobOfferRepository jobOfferRepository;
    @Mock
    AuthenticationUtils authenticationUtils;
    @Spy
    JobOfferMapper jobOfferMapper = Mappers.getMapper(JobOfferMapper.class);

    @InjectMocks
    JobOfferServiceImpl jobOfferService;

    private final JobOffer testJobOffer1 = new JobOffer();
    private final JobOffer testJobOffer2 = new JobOffer();

    AutoCloseable closeable;

    @BeforeEach
    public void setup() {
        closeable = openMocks(this);

        testJobOffer1.setId(1L);
        testJobOffer1.setDescription("Test Job Offer 1");
        testJobOffer1.setDatePublication(LocalDate.of(2022, 12, 1).atStartOfDay());
        testJobOffer1.setDateCloture(LocalDate.of(2023, 1, 1).atStartOfDay());

        testJobOffer2.setId(2L);
        testJobOffer2.setDescription("Test Job Offer 2");
        testJobOffer2.setDatePublication(LocalDate.of(2022, 11, 1).atStartOfDay());
        testJobOffer2.setDateCloture(LocalDate.of(2022, 12, 1).atStartOfDay());
    }

    @AfterEach
    void afterEach() throws Exception {
        // Clean all mocks
        closeable.close();
    }

    @DisplayName("JUnit test for addJobOffer method")
    @Test
    void givenJobOfferDto_whenAddJobOffer_thenReturnTheNewJobOffer() {
        // Given
        JobOfferDto jobOfferDto = new JobOfferDto();
        jobOfferDto.setDescription("New Job Offer");
        jobOfferDto.setDatePublication("2023-01-01");
        jobOfferDto.setDateCloture("2023-02-01");

        when(jobOfferRepository.save(Mockito.any(JobOffer.class))).thenReturn(testJobOffer1);

        // When
        JobOfferDto result = jobOfferService.addJobOffer(jobOfferDto);

        // Then
        assertThat(result).isNotNull();
        System.out.println(result);
        assertThat(result.getDescription()).isEqualTo(testJobOffer1.getDescription());

        verify(jobOfferRepository, times(1)).save(any(JobOffer.class));
    }

    @DisplayName("JUnit test for updateJobOffer method")
    @Test
    void givenValidJobOfferIdAndJobOfferDto_whenUpdateJobOffer_thenReturnTheUpdatedJobOffer() {
        // Given
        long jobOfferId = 1L;

        // Prepare the job offer to be updated
        JobOffer existingJobOffer = new JobOffer();
        existingJobOffer.setId(jobOfferId);
        existingJobOffer.setDescription("Updated Job Offer");
        existingJobOffer.setDatePublication(LocalDate.of(2022, 12, 1).atStartOfDay());
        existingJobOffer.setDateCloture(LocalDate.of(2023, 1, 1).atStartOfDay());

        when(jobOfferRepository.findById(anyLong())).thenReturn(Optional.of(existingJobOffer));
        when(jobOfferRepository.save(any(JobOffer.class))).thenReturn(existingJobOffer);

        // When
        JobOfferDto result = jobOfferService.updateJobOffer(jobOfferId, new JobOfferDto());

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getDescription()).isEqualTo(existingJobOffer.getDescription());

        verify(jobOfferRepository, times(1)).findById(jobOfferId);
        verify(jobOfferRepository, times(1)).save(any(JobOffer.class));
    }

    @DisplayName("JUnit test for updateJobOffer method")
    @Test
    void givenInvalidJobOfferId_whenUpdateJobOffer_thenThrowEntityNotFoundException() {
        // Given
        long jobOfferId = 999L;

        // Prepare the DTO
        JobOfferDto jobOfferDto = new JobOfferDto();
        jobOfferDto.setId(jobOfferId);
        jobOfferDto.setDescription("Updated Job Offer");

        when(jobOfferRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> jobOfferService.updateJobOffer(jobOfferId, jobOfferDto));

        // Then
        assertEquals("JobOffer not found with ID: " + jobOfferId, exception.getMessage());

        verify(jobOfferRepository, times(1)).findById(jobOfferId);
        verify(jobOfferRepository, never()).save(any(JobOffer.class));
    }

    @DisplayName("JUnit test for deleteJobOffer method")
    @Test
    void givenValidJobOfferId_whenDeleteJobOffer_thenDeleteTheJobOffer() {
        // Given
        long jobOfferId = 1L;

        when(jobOfferRepository.findById(anyLong())).thenReturn(Optional.of(testJobOffer1));

        // When
        jobOfferService.deleteJobOffer(jobOfferId);

        // Then
        verify(jobOfferRepository, times(1)).findById(jobOfferId);
        verify(jobOfferRepository, times(1)).deleteById(jobOfferId);
    }

    @DisplayName("JUnit test for deleteJobOffer method")
    @Test
    void givenInvalidJobOfferId_whenDeleteJobOffer_thenThrowEntityNotFoundException() {
        // Given
        long jobOfferId = 999L;

        when(jobOfferRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> jobOfferService.deleteJobOffer(jobOfferId));

        // Then
        assertEquals("JobOffer not found with ID: " + jobOfferId, exception.getMessage());

        verify(jobOfferRepository, times(1)).findById(jobOfferId);
        verify(jobOfferRepository, never()).deleteById(jobOfferId);
    }

    @DisplayName("JUnit test for getJobOfferById method")
    @Test
    void givenValidJobOfferId_whenGetJobOfferById_thenReturnTheJobOffer() {
        // Given
        long jobOfferId = 1L;

        when(jobOfferRepository.findById(anyLong())).thenReturn(Optional.of(testJobOffer1));

        // When
        JobOfferDto result = jobOfferService.getJobOfferById(jobOfferId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(jobOfferId);

        verify(jobOfferRepository, times(1)).findById(jobOfferId);
    }

    @DisplayName("JUnit test for getJobOfferById method")
    @Test
    void givenInvalidJobOfferId_whenGetJobOfferById_thenThrowEntityNotFoundException() {
        // Given
        long jobOfferId = 999L;

        when(jobOfferRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> jobOfferService.getJobOfferById(jobOfferId));

        // Then
        assertEquals("JobOffer not found with ID: " + jobOfferId, exception.getMessage());

        verify(jobOfferRepository, times(1)).findById(jobOfferId);
    }

    @DisplayName("JUnit test for jobOfferList method")
    @Test
    void whenJobOfferList_thenReturnListOfJobOffers() {
        // Given
        List<JobOffer> jobOffers = Arrays.asList(testJobOffer1, testJobOffer2);

        when(jobOfferRepository.findAll()).thenReturn(jobOffers);

        // When
        List<JobOfferDto> result = jobOfferService.jobOfferList();

        // Then
        assertThat(result.size()).isEqualTo(jobOffers.size());

        verify(jobOfferRepository, times(1)).findAll();
    }
}
