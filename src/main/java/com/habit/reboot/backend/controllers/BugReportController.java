package com.habit.reboot.backend.controllers;

import com.habit.reboot.backend.models.dtos.BugReportDto;
import com.habit.reboot.backend.services.BugReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/bug-report")
@RestController
public class BugReportController {

    private final BugReportService bugReportService;

    BugReportController(BugReportService bugReportService) {
        this.bugReportService = bugReportService;
    }

    @GetMapping()
    public List<BugReportDto> getBugReports() {
        return bugReportService.getBugReportList();
    }

    @GetMapping("/{id}")
    public BugReportDto getBugReport(@PathVariable long id) {
        return bugReportService.getBugReport(id);
    }

    @PostMapping
    public BugReportDto createBugReport(@RequestBody BugReportDto bugReport) {
        return bugReportService.createBugReport(bugReport);
    }

}
