package com.habit.reboot.backend.services;

import com.habit.reboot.backend.models.dtos.BugReportDto;
import com.habit.reboot.backend.models.entities.BugReport;
import com.habit.reboot.backend.repositories.BugReportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BugReportService {

    private final BugReportRepository bugReportRepository;

    public BugReportService(BugReportRepository bugReportRepository) {
        this.bugReportRepository = bugReportRepository;
    }

    public BugReportDto createBugReport(BugReportDto bugReportDto) {
        BugReport bugReport = bugReportRepository.save(toEntity(bugReportDto));
        return toDto(bugReport);
    }

    public BugReportDto getBugReport(long id) {
        return toDto(getEntity(id));
    }

    public List<BugReportDto> getBugReportList() {
        List<BugReport> bugReports = bugReportRepository.findAll();
        List<BugReportDto> result = new ArrayList<>();
        for (BugReport bugReport : bugReports) {
            BugReportDto dto = toDto(bugReport);
            result.add(dto);
        }
        return result;
    }

    private BugReport getEntity(long id) {
        Optional<BugReport> bugReportOptional = bugReportRepository.findById(id);
        if (bugReportOptional.isPresent()) {
            return bugReportOptional.get();
        }

        throw new RuntimeException("BugReport with id:" + id + " does not exist!");
    }

    private static BugReportDto toDto(BugReport bugReport) {
        return new BugReportDto(bugReport.getId(),
                bugReport.getTitle(),
                bugReport.getImage(),
                bugReport.getDescription());
    }

    private static BugReport toEntity(BugReportDto bugReportDto) {
        BugReport bugReport = new BugReport();
        bugReport.setTitle(bugReportDto.getTitle());
        bugReport.setImage(bugReportDto.getImage());
        bugReport.setDescription(bugReportDto.getDescription());
        return bugReport;
    }
}
