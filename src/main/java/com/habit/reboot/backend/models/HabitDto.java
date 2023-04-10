package com.habit.reboot.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class HabitDto {
    private Long id;
    private String title;
    private Date startTime;
    private Long timeSpan;
}
