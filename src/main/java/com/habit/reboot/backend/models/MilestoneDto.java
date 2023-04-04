package com.habit.reboot.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class MilestoneDto {
    private Long id;
    private String title;
    private String color;
    private Date time;
    private Boolean reached;
}
