package org.example.schedule.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {
    private String title;
    private String name;
    private Integer pwd;
}
