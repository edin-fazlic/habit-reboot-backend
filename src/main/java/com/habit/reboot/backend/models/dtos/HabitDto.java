package com.habit.reboot.backend.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HabitDto {
    private Long id;
    private String uuid;
    private String title;
    private Long timeSpan;
}
