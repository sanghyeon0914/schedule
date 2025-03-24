package org.example.schedule.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.schedule.dto.ScheduleRequestDto;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;

    private String title; // 할일
    private String name; // 작성자
    private Integer pwd; // 비밀 번호

    private LocalDateTime createdAt; //작성일
    private LocalDateTime modifiedAt; //수정일


    public Schedule(Long id, ScheduleRequestDto dto) {
        this.id = id;
        this.title = dto.getTitle();
        this.name = dto.getName();
        this.pwd = dto.getPwd();
        this.createdAt = LocalDateTime.now(); //현재 시간이 모두 저장된다 (시, 분, 초) 주의하기
        this.modifiedAt = this.createdAt; // 최초에는 동일
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void update(ScheduleRequestDto dto) {
        this.title = dto.getTitle();
        this.name = dto.getName();
        this.pwd = dto.getPwd();
        this.modifiedAt = LocalDateTime.now();
    }

    public void updateSchedule(ScheduleRequestDto dto) {
        this.title = dto.getTitle();
        this.name = dto.getName();
        this.modifiedAt = LocalDateTime.now();
    }
}
