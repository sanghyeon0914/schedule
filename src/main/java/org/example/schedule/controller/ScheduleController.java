package org.example.schedule.controller;
import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController //@Controller + @ResponseBody
@RequestMapping("/schedule") // Prefix
public class ScheduleController {

    // 주입된 의존성을 변경할 수 없어 객체의 상태를 안전하게 유지할 수 있다.
    private final Map<Long, Schedule> scheduleList = new HashMap<>();


    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        // scheduleId 식별자 계산
        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;

        // 요청받은 데이터로 schedule 객체 생성
        Schedule schedule = new Schedule(scheduleId, requestDto);

        // Inmemory schedule 저장
        scheduleList.put(scheduleId, schedule);

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ScheduleResponseDto> findAllSchedules(){

        //init List
        List<ScheduleResponseDto> responseList = new ArrayList<>();

        //HashMap<Schedule> -> List<ScheduleResponseDto>
        for(Schedule schedule : scheduleList.values()){
            ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
            responseList.add(responseDto);
        }

        // Map to List
        // responseList = scheduleList.values().stream().map(ScheduleResponseDto::new).toList();

        return responseList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> finsScheduleId(@PathVariable Long id) {

        Schedule schedule = scheduleList.get(id);

        if(schedule == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateScheduleById(
            @PathVariable long id,
            @RequestBody ScheduleRequestDto dto
    ) {
       Schedule schedule = scheduleList.get(id);

       //NPE 방지
        if(schedule == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(dto.getTitle() == null || dto.getName() == null || dto.getPwd() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

       schedule.update(dto);

       return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateTitle(
            @PathVariable long id,
            @RequestBody ScheduleRequestDto dto
    ){
        Schedule schedule = scheduleList.get(id);

        //NPE 방지
        if(schedule == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(dto.getTitle() == null || dto.getName() != null || dto.getPwd() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        schedule.updateTitle(dto);

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable long id){

        // scheduleLis의 Key값에 id를 포함하고 있다면
        if(scheduleList.containsKey(id)){
            scheduleList.remove(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
