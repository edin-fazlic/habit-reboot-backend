package com.habit.reboot.backend.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BugReportDto {
    private Long id;
    private String title;
    private String image;
    private String description;
}
