package com.habit.reboot.backend.services;

import com.habit.reboot.backend.models.MilestoneDto;
import com.habit.reboot.backend.models.entities.Milestone;
import com.habit.reboot.backend.repositories.MilestoneRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;

    public MilestoneService(MilestoneRepository milestoneRepository) {
        this.milestoneRepository = milestoneRepository;
    }

    public MilestoneDto createMilestone(MilestoneDto milestoneDto) {
        Milestone entity = toEntity(milestoneDto);
        Milestone milestone = milestoneRepository.save(entity);

        return toDto(milestone);
    }

    public List<MilestoneDto> getMilestoneList() {
        List<Milestone> milestones = milestoneRepository.findAll();
        List<MilestoneDto> result = new ArrayList<>();
        for (Milestone milestone : milestones) {
            result.add(toDto(milestone));
        }
        return result;
    }

    public MilestoneDto getMilestone(long id) {
        Milestone entity = getEntity(id);
        return toDto(entity);
    }

    public MilestoneDto updateMilestone(long id, MilestoneDto dto) {
        Milestone entity = getEntity(id);
        entity.setTitle(dto.getTitle());
        entity.setColor(dto.getColor());
        entity.setTime(dto.getTime());

        Milestone milestone = milestoneRepository.save(entity);

        return toDto(milestone);
    }

    public void deleteMilestone(long id) {
        milestoneRepository.deleteById(id);
    }

    private static MilestoneDto toDto(Milestone milestone) {
        return new MilestoneDto(milestone.getId(),
                milestone.getTitle(),
                milestone.getColor(),
                milestone.getTime(),
                false);
    }

    private static Milestone toEntity(MilestoneDto milestoneDto) {
        Milestone milestone = new Milestone();
        milestone.setTitle(milestoneDto.getTitle());
        milestone.setColor(milestoneDto.getColor());
        milestone.setTime(milestoneDto.getTime());
        return milestone;
    }

    private Milestone getEntity(long id) {
        Optional<Milestone> milestoneOptional = milestoneRepository.findById(id);
        if(milestoneOptional.isPresent()) {
            return milestoneOptional.get();
        }

        throw new RuntimeException("Milestone with id:" + id + " does not exist!");
    }
}
