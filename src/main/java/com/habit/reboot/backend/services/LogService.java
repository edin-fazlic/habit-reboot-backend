package com.habit.reboot.backend.services;

import com.habit.reboot.backend.models.dtos.LogDto;
import com.habit.reboot.backend.models.entities.Habit;
import com.habit.reboot.backend.models.entities.Log;
import com.habit.reboot.backend.repositories.LogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LogService {

    private final LogRepository logRepository;
    private final HabitService habitService;

    public LogService(LogRepository logRepository,
                      HabitService milestoneService) {
        this.logRepository = logRepository;
        this.habitService = milestoneService;
    }

    public LogDto createLog(String habitUuid, LogDto logDto) {
        Habit habit = habitService.breakHabit(habitUuid);
        Log entity = toEntity(logDto);
        entity.setHabit(habit);
        entity.setTime(new Date());
        Log log = logRepository.save(entity);

        return toDto(log);
    }

    public List<LogDto> getLogList(String habitUuid) {
        List<Log> logs = logRepository.findAllByHabitUuidOrderByIdDesc(habitUuid);
        List<LogDto> result = new ArrayList<>();
        for (Log log : logs) {
            result.add(toDto(log));
        }
        return result;
    }

    private static LogDto toDto(Log log) {
        return new LogDto(log.getTime(),
                log.getReason());
    }

    private static Log toEntity(LogDto logDto) {
        Log log = new Log();
        log.setReason(logDto.getReason());
        return log;
    }
}
