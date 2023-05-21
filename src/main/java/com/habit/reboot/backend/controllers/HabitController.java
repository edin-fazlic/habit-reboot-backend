package com.habit.reboot.backend.controllers;

import com.habit.reboot.backend.models.dtos.HabitDto;
import com.habit.reboot.backend.services.HabitService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/habit")
@RestController
public class HabitController {

    private final HabitService habitService;

    HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping("/{uuid}")
    public HabitDto getHabit(@PathVariable String uuid) {
        return habitService.getHabitByUuid(uuid);
    }

    @PostMapping
    public HabitDto createHabit(@RequestBody HabitDto habit) {
        return habitService.createHabit(habit);
    }

}
