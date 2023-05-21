package com.habit.reboot.backend.repositories;

import com.habit.reboot.backend.models.entities.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    Optional<Habit> findByUuid(String uuid);
}
