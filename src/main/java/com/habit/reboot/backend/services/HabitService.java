package com.habit.reboot.backend.services;

import com.habit.reboot.backend.models.HabitDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class HabitService {


    private Map<Long, HabitDto> habits = new HashMap<>();
    private long lastHabitId = 0;

    public HabitDto createHabit(HabitDto habit) {
        habit.setId(lastHabitId++);
        habit.setStartTime(new Date());
        habits.put(habit.getId(), habit);
        return habit;
    }

    public HabitDto getHabit(long id) {
        HabitDto habit = habits.get(id);
        if(habit == null) {
            return null;
        }
        habit.setTimeSpan((new Date().getTime() - habit.getStartTime().getTime()) / 1000);
        return habit;
    }

}
