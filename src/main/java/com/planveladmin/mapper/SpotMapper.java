package com.planveladmin.mapper;

import com.planveladmin.domain.Spot;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SpotMapper {

  @Insert("INSERT INTO spots (region_id, spot_name, address, type, image_url, thumbnail_url) " +
      "VALUES (#{regionId}, #{spotName}, #{address}, #{type}, #{imageUrl}, #{thumbnailUrl})")
  int insertSpot(Spot spot);

  @Select("SELECT * FROM spots")
  List<Spot> selectAll();

  @Select("SELECT * FROM spots WHERE region_id = #{regionId}")
  List<Spot> selectByRegion(int regionId);

  @Select("SELECT * FROM spots WHERE id = #{id}")
  Spot selectById(int id);
}