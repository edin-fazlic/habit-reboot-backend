package com.habit.reboot.backend.controllers;

import com.habit.reboot.backend.models.dtos.LogDto;
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

    @GetMapping("/habit/{habitUuid}/list")
    public List<LogDto> getLogs(@PathVariable String habitUuid) {
        return logService.getLogList(habitUuid);
    }

    @PostMapping("/habit/{habitUuid}")
    public LogDto createLog(@PathVariable String habitUuid, @RequestBody LogDto log) {
        return logService.createLog(habitUuid, log);
    }

}
