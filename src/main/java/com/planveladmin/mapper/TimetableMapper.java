package com.planveladmin.mapper;

import com.planveladmin.domain.Timetable;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimetableMapper {

  List<Timetable> searchTimetables(String transportType, Integer departureStationId, Integer arrivalStationId);

  Timetable getTimetableById(int id);

  void updateTimetable(Timetable existingTimetable);

  List<Timetable> searchByCondition(String transportType, String timeFrom, String timeTo, String transportNumber);
}
