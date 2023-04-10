package com.habit.reboot.backend.controllers;

import com.habit.reboot.backend.models.MilestoneDto;
import com.habit.reboot.backend.services.MilestoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/milestone")
@RestController
public class MilestoneController {

    private final MilestoneService milestoneService;

    MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @GetMapping("/{habitId}/list")
    public List<MilestoneDto> getMilestones(@PathVariable long habitId) {
        return milestoneService.getMilestoneList();
    }

    @GetMapping("/{id}")
    public MilestoneDto getMilestone(@PathVariable long id) {
        return milestoneService.getMilestone(id);
    }

    @PostMapping
    public MilestoneDto createMilestone(@RequestBody MilestoneDto milestone) {
        return milestoneService.createMilestone(milestone);
    }

    @PutMapping("/{id}")
    public MilestoneDto updateMilestone(@PathVariable long id, @RequestBody MilestoneDto milestone) {
        return milestoneService.updateMilestone(id, milestone);
    }

    @DeleteMapping("/{id}")
    public void deleteMilestone(@PathVariable long id) {
        milestoneService.deleteMilestone(id);
    }
}
