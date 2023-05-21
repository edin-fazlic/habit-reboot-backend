package com.habit.reboot.backend.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class LogDto {
    private Date time;
    private String reason;
}
