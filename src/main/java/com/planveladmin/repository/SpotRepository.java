package com.planveladmin.repository;

import com.planveladmin.domain.Spot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Integer> {

    List<Spot> findByRegionIdOrderByIdDesc(Integer regionId);

    List<Spot> findByTypeOrderByIdDesc(String type);

    List<Spot> findByRegionIdAndTypeOrderByIdDesc(Integer regionId, String type);

    List<Spot> findBySpotNameContainingIgnoreCaseOrderByIdDesc(String spotName);

    Page<Spot> findByTypeOrderByIdDesc(String type, Pageable pageable);
    Page<Spot> findByRegionIdOrderByIdDesc(Integer regionId, Pageable pageable);
    Page<Spot> findAllByOrderByIdDesc(Pageable pageable);
}
