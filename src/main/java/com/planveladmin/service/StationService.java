package com.planveladmin.service;

import com.planveladmin.domain.Station;
import com.planveladmin.mapper.StationMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StationService {

  private final StationMapper stationMapper;

  public List<Station> getStationsByType(String type) {
    return stationMapper.getStationsByType(type);
  }
}
