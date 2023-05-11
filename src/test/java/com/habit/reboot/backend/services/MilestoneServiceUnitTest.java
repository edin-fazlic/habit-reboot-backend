package com.habit.reboot.backend.services;


import com.habit.reboot.backend.data.MilestoneTest;
import com.habit.reboot.backend.models.MilestoneDto;
import com.habit.reboot.backend.models.entities.Milestone;
import com.habit.reboot.backend.repositories.MilestoneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class MilestoneServiceUnitTest {

    @MockBean
    private MilestoneRepository milestoneRepository;

    @TestConfiguration
    static class MilestoneServiceTestConfiguration {

        @Bean
        @Primary
        public MilestoneService milestoneService(MilestoneRepository milestoneRepository) {
            return new MilestoneService(milestoneRepository);
        }
    }

    @Autowired
    private MilestoneService milestoneService;

    @Test
    public void givenMilestones_whenGetMilestoneList_thenListShouldBeFound() {
        // arrange
        Mockito.when(milestoneRepository.findAll())
                .thenReturn(List.of(MilestoneTest.milestone()));

        // act
        List<MilestoneDto> returnedList = milestoneService.getMilestoneList();

        // assert
        assertThat(returnedList).hasSize(1);
    }
    @Test
    public void givenNoMilestones_whenGetMilestoneList_thenListShouldBeEmpty() {
        // act
        List<MilestoneDto> returnedList = milestoneService.getMilestoneList();

        // assert
        assertThat(returnedList).isEmpty();
    }

    @Test
    public void givenValidId_whenGetMilestone_thenMilestoneShouldBeFound() {
        // arrange
        long id = 1L;
        Mockito.when(milestoneRepository.findById(id))
                .thenReturn(Optional.of(MilestoneTest.milestone()));

        // act
        MilestoneDto resultMilestone = milestoneService.getMilestone(id);

        // assert
        assertThat(resultMilestone.getTitle()).isEqualTo("Full day");
    }

    @Test
    public void givenInvalidId_whenGetMilestone_thenExceptionShouldBeThrown() {
        assertThatThrownBy(() -> milestoneService.getMilestone(3L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    public void givenMilestone_whenCreateMilestone_thenMilestoneIsReturned() {
        // arrange
        MilestoneDto inputMilestoneDto = MilestoneTest.milestoneDto1();
        inputMilestoneDto.setId(0L); // reset id
        Milestone outputMilestone = MilestoneTest.milestone();

        Mockito.when(milestoneRepository.save(any(Milestone.class)))
                .thenReturn(outputMilestone);

        // act
        MilestoneDto resultMilestone = milestoneService.createMilestone(inputMilestoneDto);

        // assert
        assertThat(resultMilestone).isNotNull();
        assertThat(resultMilestone.getTitle()).isEqualTo(inputMilestoneDto.getTitle());
        assertThat(resultMilestone.getId()).isNotEqualTo(0L);
    }

    @Test
    public void givenMilestone_whenCreateMilestone_thenRepositoryCalled() {
        // arrange
        MilestoneDto milestoneDto = MilestoneTest.milestoneDto3();

        Mockito.when(milestoneRepository.save(any(Milestone.class)))
                .thenReturn(MilestoneTest.milestone());

        // act
        milestoneService.createMilestone(milestoneDto);

        // assert
        verify(milestoneRepository, times(1)).save(any(Milestone.class));
    }

    @Test
    public void givenMilestoneAndValidId_whenUpdate_thenMilestoneReturned() {
        // arrange
        MilestoneDto inputMilestoneDto = MilestoneTest.milestoneDto1();
        inputMilestoneDto.setId(0L); // reset id
        long id = 1L;
        Milestone outputMilestone = MilestoneTest.milestone();
        outputMilestone.setId(id);

        Mockito.when(milestoneRepository.findById(id))
                .thenReturn(Optional.of(outputMilestone));
        Mockito.when(milestoneRepository.save(any(Milestone.class)))
                .thenReturn(outputMilestone);

        // act
        MilestoneDto resultMilestone = milestoneService.updateMilestone(id, inputMilestoneDto);

        // assert
        assertThat(resultMilestone).isNotNull();
        assertThat(resultMilestone.getTitle()).isEqualTo(inputMilestoneDto.getTitle());
        assertThat(resultMilestone.getId()).isEqualTo(id);
    }

    @Test
    public void givenInvalidId_whenUpdate_thenExceptionShouldBeThrown() {
        assertThatThrownBy(() -> milestoneService.updateMilestone(2L, MilestoneTest.milestoneDto3()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    public void givenMilestone_whenDelete_thenRepositoryCalled() {
        // arrange
        long id = 2L;

        // act
        milestoneService.deleteMilestone(id);

        // assert
        verify(milestoneRepository, times(1)).deleteById(id);
    }
}
