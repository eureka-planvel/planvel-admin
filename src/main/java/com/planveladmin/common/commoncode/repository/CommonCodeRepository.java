package com.planveladmin.common.commoncode.repository;

import java.util.List;

import com.planveladmin.common.commoncode.entity.Code;
import com.planveladmin.common.commoncode.entity.key.CodeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommonCodeRepository extends JpaRepository<Code, CodeKey>{
    @Query("select c from Code c where c.codeKey.groupCode in :groupCodes order by c.codeKey.groupCode, c.orderNo")
    List<Code> findByGroupCodes(@Param("groupCodes") List<String> groupCodes);
}
