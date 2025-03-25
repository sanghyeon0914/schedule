package org.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String title;
    private String name;
    private Integer pwd;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Schedule(String title, String name, Integer pwd) {
        this.title = title;
        this.name = name;
        this.pwd = pwd;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = this.createdAt;
    }

    @CreatedDate
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @LastModifiedDate
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

}
