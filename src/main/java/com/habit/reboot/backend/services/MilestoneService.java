package com.habit.reboot.backend.services;

import com.habit.reboot.backend.models.MilestoneDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MilestoneService {

    public MilestoneDto createMilestone(MilestoneDto milestone) {
        milestone.setId(91L);
        milestone.setReached(false);
        return milestone;
    }

    public List<MilestoneDto> getMilestoneList() {
        List<MilestoneDto> result = new ArrayList<>();
        MilestoneDto x = new MilestoneDto(42L, "Progress for a week", "#FF0000", new Date(), false);
        MilestoneDto y = new MilestoneDto(103L, "Progress for a month", "#00FF00", new Date(), false);
        result.add(x);
        result.add(y);

        return result;
    }

    public MilestoneDto getMilestone(long id) {
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
