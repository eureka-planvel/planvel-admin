package com.planveladmin.common.commoncode.repository;

import com.planveladmin.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonAccommodationRepository extends JpaRepository<Accommodation, Integer> {
    List<Accommodation> findByTypeCode(String typeCode);
}
