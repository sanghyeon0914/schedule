package org.example.schedule.service;

import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


public interface ScheduleService{

    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(Long id);

    @Transactional
    ScheduleResponseDto updateSchedule(Long id, String title, String name, Integer pwd, LocalDateTime modifiedAt);

    void deleteSchedule(long id, Integer pwd);


}
