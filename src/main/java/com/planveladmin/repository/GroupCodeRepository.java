package com.planveladmin.repository;

import com.planveladmin.domain.GroupCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupCodeRepository extends JpaRepository<GroupCode, String> {
}
