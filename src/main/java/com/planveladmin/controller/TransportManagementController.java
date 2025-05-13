package com.planveladmin.controller;

import com.planveladmin.common.util.SessionUtil;
import com.planveladmin.domain.Station;
import com.planveladmin.domain.Timetable;
import com.planveladmin.dto.AdminDto;
import com.planveladmin.dto.CommonResponse;
import com.planveladmin.service.StationService;
import com.planveladmin.service.TimetableService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transport")
@RequiredArgsConstructor
public class TransportManagementController {

  private final TimetableService timetableService;
  private final StationService stationService;


  @GetMapping("/timetables")
  public ResponseEntity<CommonResponse<List<Timetable>>> getTimetables(@RequestParam  String transportType, @RequestParam Integer departureStationId, @RequestParam Integer arrivalStationId, HttpSession session){
    AdminDto admin = SessionUtil.getLoginAdmin(session);
    if(admin == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.fail("로그인 필요"));

    return ResponseEntity.ok(CommonResponse.success(timetableService.searchTimetables(transportType, departureStationId, arrivalStationId)," 시간표 조회"));
  }

  @GetMapping("/timetables/search")
  public ResponseEntity<CommonResponse<List<Timetable>>> searchTimetables(
      @RequestParam String transportType,
      @RequestParam String timeFrom,
      @RequestParam String timeTo,
      @RequestParam String transportNumber,
      HttpSession session) {

    AdminDto admin = SessionUtil.getLoginAdmin(session);
    if(admin == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.fail("로그인 필요"));

    List<Timetable> timetables = timetableService.searchByCondition(
        transportType, timeFrom, timeTo, transportNumber);

    return ResponseEntity.ok(CommonResponse.success(timetables, "조건별 시간표 조회 성공"));
  }

  @GetMapping("/timetables/{id}")
  public ResponseEntity<CommonResponse<Timetable>> getTimetable (@PathVariable int id, HttpSession session){
    AdminDto admin = SessionUtil.getLoginAdmin(session);
    if(admin == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.fail("로그인 필요"));

    return ResponseEntity.ok(CommonResponse.success(timetableService.getTimetableById(id),"시간표 정보 조회"));
  }

  @PutMapping("/timetables/{id}")
  public ResponseEntity<CommonResponse<Timetable>> updateTimetable(@PathVariable int id, @RequestBody Timetable timetable, HttpSession session) {
    AdminDto admin = SessionUtil.getLoginAdmin(session);
    if(admin == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.fail("로그인 필요"));

    return ResponseEntity.ok(CommonResponse.success(timetableService.updateTimetable(id),"시간표 정보 조회"));
  }

  @GetMapping("/stations")
  public ResponseEntity<CommonResponse<List<Station>>> getStations(@RequestParam String type, HttpSession session) {
    AdminDto admin = SessionUtil.getLoginAdmin(session);
    if(admin == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.fail("로그인 필요"));

    return ResponseEntity.ok(CommonResponse.success(stationService.getStationsByType(type), "역 조회 성공"));
  }

}
