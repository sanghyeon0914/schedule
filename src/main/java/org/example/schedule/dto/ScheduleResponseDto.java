package org.example.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule.entity.Schedule;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String title;
    private String name;
    private Integer pwd;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.name = schedule.getName();
        this.pwd = schedule.getPwd();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
