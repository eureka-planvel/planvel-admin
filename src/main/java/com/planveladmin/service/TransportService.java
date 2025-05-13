package com.planveladmin.service;

import com.planveladmin.domain.Timetable;
import com.planveladmin.mapper.TransportMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransportService {

  private final TransportMapper transportMapper;

  public List<Timetable> searchTimetables(String transportType, Integer departureStationId, Integer arrivalStationId) {
    return transportMapper.searchTimetables(transportType, departureStationId, arrivalStationId);
  }

  public Timetable getTimetableById(int id) {
    return transportMapper.getTimetableById(id);
  }

  public Timetable updateTimetable(int id) {
    Timetable existingTimetable = transportMapper.getTimetableById(id);
    if (existingTimetable != null) {
      transportMapper.updateTimetable(existingTimetable);
      return existingTimetable;
    }
    return null;
  }

  public List<Timetable> searchByCondition(String transportType, String timeFrom, String timeTo, String transportNumber) {
    return transportMapper.searchByCondition(transportType, timeFrom, timeTo, transportNumber);
  }
}
