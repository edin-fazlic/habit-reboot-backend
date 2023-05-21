package com.habit.reboot.backend.controllers;

import com.habit.reboot.backend.models.dtos.MilestoneDto;
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

    @GetMapping("/habit/{habitUuid}/list")
    public List<MilestoneDto> getMilestones(@PathVariable String habitUuid) {
        return milestoneService.getMilestoneList(habitUuid);
    }

    @GetMapping("/{id}")
    public MilestoneDto getMilestone(@PathVariable long id) {
        return milestoneService.getMilestone(id);
    }

    @PostMapping("/habit/{habitUuid}")
    public MilestoneDto createMilestone(@PathVariable String habitUuid, @RequestBody MilestoneDto milestone) {
        return milestoneService.createMilestone(habitUuid, milestone);
    }

    @PutMapping("/habit/{habitUuid}/{id}")
    public MilestoneDto updateMilestone(@PathVariable String habitUuid, @PathVariable long id, @RequestBody MilestoneDto milestone) {
        return milestoneService.updateMilestone(habitUuid, id, milestone);
    }

    @DeleteMapping("/{id}")
    public void deleteMilestone(@PathVariable long id) {
        milestoneService.deleteMilestone(id);
    }
}
