package com.planveladmin.service;

import com.planveladmin.domain.Timetable;
import com.planveladmin.mapper.TimetableMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimetableService {

  private final TimetableMapper timetableMapper;

  public List<Timetable> searchTimetables(String transportType, Integer departureStationId, Integer arrivalStationId) {
    return timetableMapper.searchTimetables(transportType, departureStationId, arrivalStationId);
  }

  public Timetable getTimetableById(int id) {
    return timetableMapper.getTimetableById(id);
  }

  public Timetable updateTimetable(int id) {
    Timetable existingTimetable = timetableMapper.getTimetableById(id);
    if (existingTimetable != null) {
      timetableMapper.updateTimetable(existingTimetable);
      return existingTimetable;
    }
    return null;
  }

  public List<Timetable> searchByCondition(String transportType, String timeFrom, String timeTo, String transportNumber) {
    return timetableMapper.searchByCondition(transportType, timeFrom, timeTo, transportNumber);
  }
}
