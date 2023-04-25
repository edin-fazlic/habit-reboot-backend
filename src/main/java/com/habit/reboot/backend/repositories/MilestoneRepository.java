package com.habit.reboot.backend.repositories;

import com.habit.reboot.backend.models.entities.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    List<Milestone> findAllByColor(String color);
    List<Milestone> findAllByColorIn(List<String> colors);

}
