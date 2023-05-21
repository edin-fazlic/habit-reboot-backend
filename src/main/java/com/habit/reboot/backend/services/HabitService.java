package com.habit.reboot.backend.services;

import com.habit.reboot.backend.models.dtos.HabitDto;
import com.habit.reboot.backend.models.entities.Habit;
import com.habit.reboot.backend.repositories.HabitRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public HabitDto createHabit(HabitDto habitDto) {
        Habit entity = toEntity(habitDto);
        entity.setTimeStart(new Date());
        entity.setUuid(UUID.randomUUID().toString());
        Habit habit = habitRepository.save(entity);

        return toDto(habit);
    }

    public HabitDto getHabitByUuid(String uuid) {
        return toDto(getEntityByUuid(uuid));
    }

    public Habit breakHabit(String habitUuid) {
        Habit entity = getEntityByUuid(habitUuid);
        entity.setTimeStart(new Date());

        return habitRepository.save(entity);
    }

    public Habit getEntityByUuid(String uuid) {
        Optional<Habit> habitOptional = habitRepository.findByUuid(uuid);
        if (habitOptional.isPresent()) {
            return habitOptional.get();
        }

        throw new RuntimeException("Habit with uuid:" + uuid + " does not exist!");
    }

    private static HabitDto toDto(Habit habit) {
        return new HabitDto(habit.getId(),
                habit.getUuid(),
                habit.getTitle(),
                (new Date().getTime() - habit.getTimeStart().getTime()) / 1000);
    }

    private static Habit toEntity(HabitDto habitDto) {
        Habit habit = new Habit();
        habit.setTitle(habitDto.getTitle());
        return habit;
    }
}
