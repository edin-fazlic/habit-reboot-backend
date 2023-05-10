package com.habit.reboot.backend.services;


import com.habit.reboot.backend.data.MilestoneTest;
import com.habit.reboot.backend.models.MilestoneDto;
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

}
