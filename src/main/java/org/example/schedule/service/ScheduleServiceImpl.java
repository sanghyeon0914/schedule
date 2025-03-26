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
        }

        @Transactional
        @Override
        public ScheduleResponseDto updateSchedule(Long id, String title, String name, Integer pwd, LocalDateTime modifiedAt) {

            if (id == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must not be null");
            }

            Schedule schedule = scheduleRepository.findScheduleById(id);

            if (schedule == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found with id = " + id);
            }

            if(!schedule.getPwd().equals(pwd)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pwd is wrong");
            }

            int updatedRaw = scheduleRepository.updateSchedule(id, title, name, pwd, modifiedAt);

            if (updatedRaw == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
            }

            return new ScheduleResponseDto(scheduleRepository.findScheduleById(id));
        }

        @Override
        public void deleteSchedule(long id, Integer pwd){

            Schedule schedule = scheduleRepository.findScheduleById(id);
            if (schedule == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found with id = " + id);
            }

            if(!schedule.getPwd().equals(pwd)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pwd is wrong");
            }

            int deleteRow = scheduleRepository.deleteSchedule(id, pwd);

            if(deleteRow == 0){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
            }
        }


}

