package com.habit.reboot.backend.controllers;

import com.habit.reboot.backend.models.HabitDto;
import com.habit.reboot.backend.services.HabitService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/habit")
@RestController
public class HabitController {

    private final HabitService habitService;

    HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping("/{id}")
    public HabitDto getMilestone(@PathVariable long id) {
        return habitService.getHabit(id);
    }

    @PostMapping
    public HabitDto createMilestone(@RequestBody HabitDto habit) {
        return habitService.createHabit(habit);
    }

}
