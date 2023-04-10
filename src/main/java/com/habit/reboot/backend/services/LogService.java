package com.habit.reboot.backend.services;

import com.habit.reboot.backend.models.LogDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LogService {


    private final Map<Long, List<LogDto>> logs = new HashMap<>();

    public LogDto createLog(long habitId, LogDto habit) {
        List<LogDto> habitLogs = logs.get(habitId);
        if (habitLogs == null) {
            habitLogs = new ArrayList<>();
            logs.put(habitId, habitLogs);
        }
        habitLogs.add(new LogDto(new Date(), habit.getReason()));
        return habitLogs.get(habitLogs.size() - 1);
    }

    public List<LogDto> getLogs(long habitId) {
        return logs.get(habitId);
    }

}
