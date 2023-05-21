package com.habit.reboot.backend.repositories;

import com.habit.reboot.backend.models.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findAllByHabitUuidOrderByIdDesc(String habitUuid);
}
