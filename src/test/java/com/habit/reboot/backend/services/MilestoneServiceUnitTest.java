package com.habit.reboot.backend.services;


import com.habit.reboot.backend.data.MilestoneTest;
import com.habit.reboot.backend.models.dtos.MilestoneDto;
import com.habit.reboot.backend.models.entities.Habit;
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

import java.util.Date;
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
    @MockBean
    private HabitService habitService;

    @TestConfiguration
    static class MilestoneServiceTestConfiguration {

        @Bean
        @Primary
        public MilestoneService milestoneService(MilestoneRepository milestoneRepository,
                                                 HabitService habitService) {
            return new MilestoneService(milestoneRepository, habitService);
        }
    }

    @Autowired
    private MilestoneService milestoneService;

    @Test
    public void givenMilestones_whenGetMilestoneList_thenListShouldBeFound() {
        // arrange
        long habitId = 11L;
        String habitUuid = "1835530b-fe07-4218-b0b2-8448045af47d";
        Habit habit = new Habit();
        habit.setId(habitId);
        habit.setUuid(habitUuid);
        habit.setTimeStart(new Date());
        Mockito.when(habitService.getEntityByUuid(habitUuid))
                .thenReturn(habit);
        Mockito.when(milestoneRepository.findAllByHabitId(habitId))
                .thenReturn(List.of(MilestoneTest.milestone()));

        // act
        List<MilestoneDto> returnedList = milestoneService.getMilestoneList(habitUuid);

        // assert
        assertThat(returnedList).hasSize(1);
    }

    @Test
    public void givenNoMilestones_whenGetMilestoneList_thenListShouldBeEmpty() {
        // arrange
        String habitUuid = "1835530b-fe07-4218-b0b2-8448045af47d";
        Mockito.when(habitService.getEntityByUuid(habitUuid))
                .thenReturn(new Habit());

        // act
        List<MilestoneDto> returnedList = milestoneService.getMilestoneList(habitUuid);

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
        String habitUuid = "18d90d3d-9f6a-4bdc-b787-432fbf1ff358";
        MilestoneDto inputMilestoneDto = MilestoneTest.milestoneDto1();
        inputMilestoneDto.setId(0L); // reset id
        Milestone outputMilestone = MilestoneTest.milestone();

        Mockito.when(habitService.getEntityByUuid(habitUuid))
                .thenReturn(new Habit());
        Mockito.when(milestoneRepository.save(any(Milestone.class)))
                .thenReturn(outputMilestone);

        // act
        MilestoneDto resultMilestone = milestoneService.createMilestone(habitUuid, inputMilestoneDto);

        // assert
        assertThat(resultMilestone).isNotNull();
        assertThat(resultMilestone.getTitle()).isEqualTo(inputMilestoneDto.getTitle());
        assertThat(resultMilestone.getId()).isNotEqualTo(0L);
    }

    @Test
    public void givenMilestone_whenCreateMilestone_thenRepositoryCalled() {
        // arrange
        String habitUuid = "18d90d3d-9f6a-4bdc-b787-432fbf1ff358";
        MilestoneDto milestoneDto = MilestoneTest.milestoneDto3();

        Mockito.when(habitService.getEntityByUuid(habitUuid))
                .thenReturn(new Habit());
        Mockito.when(milestoneRepository.save(any(Milestone.class)))
                .thenReturn(MilestoneTest.milestone());

        // act
        milestoneService.createMilestone(habitUuid, milestoneDto);

        // assert
        verify(milestoneRepository, times(1)).save(any(Milestone.class));
    }

    @Test
    public void givenMilestoneAndValidId_whenUpdate_thenMilestoneReturned() {
        // arrange
        String habitUuid = "18d90d3d-9f6a-4bdc-b787-432fbf1ff358";
        MilestoneDto inputMilestoneDto = MilestoneTest.milestoneDto1();
        inputMilestoneDto.setId(0L); // reset id
        long id = 1L;
        Milestone outputMilestone = MilestoneTest.milestone();
        outputMilestone.setId(id);

        Mockito.when(habitService.getEntityByUuid(habitUuid))
                .thenReturn(new Habit());
        Mockito.when(milestoneRepository.findById(id))
                .thenReturn(Optional.of(outputMilestone));
        Mockito.when(milestoneRepository.save(any(Milestone.class)))
                .thenReturn(outputMilestone);

        // act
        MilestoneDto resultMilestone = milestoneService.updateMilestone(habitUuid, id, inputMilestoneDto);

        // assert
        assertThat(resultMilestone).isNotNull();
        assertThat(resultMilestone.getTitle()).isEqualTo(inputMilestoneDto.getTitle());
        assertThat(resultMilestone.getId()).isEqualTo(id);
    }

    @Test
    public void givenInvalidId_whenUpdate_thenExceptionShouldBeThrown() {
        assertThatThrownBy(() -> milestoneService.updateMilestone("18d90d3d-9f6a-4bdc-b787-432fbf1ff358", 2L, MilestoneTest.milestoneDto3()))
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
