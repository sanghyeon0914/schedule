package org.example.schedule.repository;

import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules();

    Schedule findScheduleById(Long id);

    int updateSchedule(Long id, String title, String name, Integer pwd, LocalDateTime modifiedAt);

    int deleteSchedule(Long id, Integer pwd);


}
