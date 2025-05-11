package com.planveladmin.mapper;

import com.planveladmin.domain.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

  @Select("SELECT COUNT(*) > 0 FROM admin WHERE username = #{username}")
  boolean existsByUsername(String username);

  @Insert("INSERT INTO admin (username, password, name) VALUES (#{username}, #{password}, #{name})")
  void insertAdmin(Admin admin);

  @Select("SELECT * FROM admin WHERE username = #{username}")
  Admin findByUsername(String username);
}
