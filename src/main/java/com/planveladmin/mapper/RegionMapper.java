package com.planveladmin.mapper;

import com.planveladmin.domain.Region;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RegionMapper {

  @Select("SELECT id, name FROM region")
  List<Region> selectAllRegions();

}
