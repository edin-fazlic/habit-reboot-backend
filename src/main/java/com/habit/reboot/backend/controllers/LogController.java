package com.habit.reboot.backend.controllers;

import com.habit.reboot.backend.models.LogDto;
import com.habit.reboot.backend.services.LogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/log")
@RestController
public class LogController {

    private final LogService logService;

    LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/{habitId}/list")
    public List<LogDto> getMilestone(@PathVariable long habitId) {
        return logService.getLogs(habitId);
    }

    @PostMapping("/{habitId}")
    public LogDto createMilestone(@PathVariable long habitId, @RequestBody LogDto log) {
        return logService.createLog(habitId, log);
    }

}
