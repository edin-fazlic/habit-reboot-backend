package com.habit.reboot.backend.repositories;

import com.habit.reboot.backend.models.entities.BugReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugReportRepository extends JpaRepository<BugReport, Long> {
}
