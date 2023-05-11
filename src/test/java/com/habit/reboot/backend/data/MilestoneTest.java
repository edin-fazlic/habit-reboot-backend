package com.habit.reboot.backend.data;

import com.habit.reboot.backend.models.MilestoneDto;
import com.habit.reboot.backend.models.entities.Milestone;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MilestoneTest {

    private static long id1 = 1L;
    private static String title1 = "Full day";
    private static String color1 = "#FF9956";
    private static Date time1 = new Date();

    public static Milestone milestone() {
        Milestone milestone = new Milestone();
        milestone.setId(id1);
        milestone.setTitle(title1);
        milestone.setColor(color1);
        milestone.setTime(time1);

        return milestone;
    }

    public static MilestoneDto milestoneDto1() {
        return new MilestoneDto(id1, title1, color1, time1, false);
    }

    public static MilestoneDto milestoneDto3() {
        return new MilestoneDto(3L, "One week", "#00C122", new Date(), false);
    }
}
