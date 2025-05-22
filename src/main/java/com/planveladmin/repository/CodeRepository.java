package com.planveladmin.repository;

import com.planveladmin.domain.Code;
import com.planveladmin.domain.CodeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, CodeKey> {

    @Query("SELECT c FROM Code c WHERE c.codeKey.groupCode = :groupCode ORDER BY c.orderNo")
    List<Code> findByGroupCodeOrderByOrderNo(@Param("groupCode") String groupCode);

    @Query("SELECT c FROM Code c WHERE c.codeKey.groupCode = :groupCode AND c.codeKey.code = :code")
    Optional<Code> findByGroupCodeAndCode(@Param("groupCode") String groupCode, @Param("code") String code);
}
