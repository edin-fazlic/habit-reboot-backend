package com.habit.reboot.backend.services;

import com.habit.reboot.backend.models.MilestoneDto;
import com.habit.reboot.backend.models.entities.Milestone;
import com.habit.reboot.backend.repositories.MilestoneRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;

    public MilestoneService(MilestoneRepository milestoneRepository) {
        this.milestoneRepository = milestoneRepository;
    }

    public MilestoneDto createMilestone(MilestoneDto milestoneDto) {
        Milestone milestone = new Milestone();
        milestone.setTitle(milestoneDto.getTitle());
        milestone.setColor(milestoneDto.getColor());
        milestone.setTime(milestoneDto.getTime());
        milestoneRepository.save(milestone);

        milestoneDto.setId(milestone.getId());

        return milestoneDto;
    }


    public List<MilestoneDto> getMilestoneList() {
        List<Milestone> milestones = milestoneRepository.findAll();
        List<MilestoneDto> result = new ArrayList<>();
        for (Milestone milestone : milestones) {
            result.add(new MilestoneDto(milestone.getId(),
                    milestone.getTitle(),
                    milestone.getColor(),
                    milestone.getTime(),
                    false));
        }
        return result;
    }

    public MilestoneDto getMilestone(long id) {
        milestoneRepository.getById(id);

        return new MilestoneDto(id, "Progress for a week", "#FF0000", new Date(), false);
    }

    public MilestoneDto updateMilestone(long id, MilestoneDto milestone) {
        System.out.println("Milestone found for a give id: " + id);
        milestone.setId(id);
        milestone.setReached(true);
        return milestone;
    }

    public void deleteMilestone(long id) {
        System.out.println("Deleted " + id);
    }
}
