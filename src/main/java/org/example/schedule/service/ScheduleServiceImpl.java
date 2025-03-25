package org.example.schedule.service;

import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;
import org.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService{

        private final ScheduleRepository scheduleRepository;

        public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
            this.scheduleRepository = scheduleRepository;
        }

        @Override
        public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {

            Schedule schedule = new Schedule(requestDto.getTitle(), requestDto.getName(),requestDto.getPwd());

            return scheduleRepository.saveSchedule(schedule);
        }

        @Override
        public List<ScheduleResponseDto> findAllSchedules() {

            List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules();

            return allSchedules;
        }

        @Override
        public ScheduleResponseDto findScheduleById(Long id) {

            Schedule schedule = scheduleRepository.findScheduleById(id);

            return new ScheduleResponseDto(schedule);

            /*Optional<Schedule> optiponalSchedule = ScheduleRepository.findScheduleById(id);

            if (optiponalSchedule.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
            }

            return new ScheduleResponseDto(optiponalSchedule.get());*/
        }
}

