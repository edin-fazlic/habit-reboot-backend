package com.habit.reboot.backend.services;

import com.habit.reboot.backend.models.dtos.MilestoneDto;
import com.habit.reboot.backend.models.entities.Habit;
import com.habit.reboot.backend.models.entities.Milestone;
import com.habit.reboot.backend.repositories.MilestoneRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final HabitService habitService;

    public MilestoneService(MilestoneRepository milestoneRepository,
                            HabitService habitService) {
        this.milestoneRepository = milestoneRepository;
        this.habitService = habitService;
    }

    public MilestoneDto createMilestone(String habitUuid, MilestoneDto milestoneDto) {
        Habit habit = habitService.getEntityByUuid(habitUuid);
        Milestone entity = toEntity(milestoneDto);
        entity.setHabit(habit);
        Milestone milestone = milestoneRepository.save(entity);

        return toDto(milestone);
    }

    public List<MilestoneDto> getMilestoneList(String habitUuid) {
        Habit habit = habitService.getEntityByUuid(habitUuid);
        List<Milestone> milestones = milestoneRepository.findAllByHabitId(habit.getId());
        List<MilestoneDto> result = new ArrayList<>();
        for (Milestone milestone : milestones) {
            MilestoneDto dto = toDto(milestone);
            long timeSinceStart = new Date().getTime() - habit.getTimeStart().getTime();
            dto.setReached(timeSinceStart / 1000 > milestone.getTime());
            result.add(dto);
        }
        return result;
    }

    public MilestoneDto getMilestone(long id) {
        Milestone entity = getEntity(id);
        return toDto(entity);
    }

    public MilestoneDto updateMilestone(String habitUuid, long id, MilestoneDto dto) {
        Habit habit = habitService.getEntityByUuid(habitUuid);
        Milestone entity = getEntity(id);
        entity.setHabit(habit);
        entity.setTitle(dto.getTitle());
        entity.setColor(dto.getColor());
        entity.setTime(dto.getTime());

        Milestone milestone = milestoneRepository.save(entity);

        return toDto(milestone);
    }

    public void deleteMilestone(long id) {
        milestoneRepository.deleteById(id);
    }

    private Milestone getEntity(long id) {
        Optional<Milestone> milestoneOptional = milestoneRepository.findById(id);
        if (milestoneOptional.isPresent()) {
            return milestoneOptional.get();
        }

        throw new RuntimeException("Milestone with id:" + id + " does not exist!");
    }

    private static MilestoneDto toDto(Milestone milestone) {
        MilestoneDto dto = new MilestoneDto();
        dto.setId(milestone.getId());
        dto.setTitle(milestone.getTitle());
        dto.setColor(milestone.getColor());
        dto.setTime(milestone.getTime());
        return dto;
    }

    private static Milestone toEntity(MilestoneDto milestoneDto) {
        Milestone milestone = new Milestone();
        milestone.setTitle(milestoneDto.getTitle());
        milestone.setColor(milestoneDto.getColor());
        milestone.setTime(milestoneDto.getTime());
        return milestone;
    }
}
