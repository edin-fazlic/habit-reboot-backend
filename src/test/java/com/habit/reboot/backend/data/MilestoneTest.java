package com.habit.reboot.backend.data;

import com.habit.reboot.backend.models.entities.Milestone;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MilestoneTest {

    public static Milestone milestone() {
        Milestone milestone = new Milestone();
        milestone.setId(1L);
        milestone.setTitle("Full day");
        milestone.setColor("#FF9956");
        milestone.setTime(new Date());

        return milestone;
    }
}
