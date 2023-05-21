package com.habit.reboot.backend.data;

import com.habit.reboot.backend.models.dtos.MilestoneDto;
import com.habit.reboot.backend.models.entities.Milestone;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MilestoneTest {

    private static long id1 = 1L;
    private static String title1 = "Full day";
    private static String color1 = "#FF9956";
    private static Long time1 = 600L; // 10 min

    public static Milestone milestone() {
        Milestone milestone = new Milestone();
        milestone.setId(id1);
        milestone.setTitle(title1);
        milestone.setColor(color1);
        milestone.setTime(time1);

        return milestone;
    }

    public static MilestoneDto milestoneDto1() {
        MilestoneDto dto = new MilestoneDto();
        dto.setId(id1);
        dto.setTitle(title1);
        dto.setColor(color1);
        dto.setTime(time1);
        return dto;
    }

    public static MilestoneDto milestoneDto3() {
        MilestoneDto dto = new MilestoneDto();
        dto.setId(3L);
        dto.setTitle("One week");
        dto.setColor("#00C122");
        dto.setTime(30L);
        return dto;
    }
}
