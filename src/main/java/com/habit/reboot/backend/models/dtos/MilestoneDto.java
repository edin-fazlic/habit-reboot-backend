package com.habit.reboot.backend.models.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MilestoneDto {
    private Long id;
    private String title;
    private String color;
    private Long time;
    private Boolean reached;
}
