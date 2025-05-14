package com.planveladmin.mapper;

import com.planveladmin.domain.Accommodation;
import com.planveladmin.dto.request.AccommodationRequestDto;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccommodationMapper {

  int insertAccommodation(Accommodation accommodation);

  @Select("SELECT * FROM accommodations")
  List<Accommodation> selectAll();

  @Select("SELECT * FROM accommodations WHERE region_id = #{regionId}")
  List<Accommodation> selectByRegion(@Param("regionId") int regionId);

  Accommodation selectById(@Param("id") int id);
}
