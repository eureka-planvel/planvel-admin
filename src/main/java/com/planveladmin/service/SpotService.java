package com.planveladmin.service;

import com.planveladmin.domain.Spot;
import com.planveladmin.domain.type.SpotType;
import com.planveladmin.dto.*;
import com.planveladmin.mapper.SpotMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.planveladmin.repository.RegionRepository;
import com.planveladmin.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SpotService {

  private final SpotRepository spotRepository;
  private final CodeService codeService;
  private final RegionRepository regionRepository;

  @Transactional
  public SpotDto createSpot(SpotCreateDto createDto) {
    validateSpotCreateDto(createDto);

    if (!codeService.existsCode("030", createDto.getType())) {
      throw new IllegalArgumentException("유효하지 않은 타입입니다: " + createDto.getType());
    }

    if (!regionRepository.existsById(createDto.getRegionId())) {
      throw new IllegalArgumentException("유효하지 않은 지역입니다: " + createDto.getRegionId());
    }

    Spot spot = createDto.toEntity();
    Spot savedSpot = spotRepository.save(spot);
    return convertToDto(savedSpot);
  }

  public SpotDto getSpot(Integer id) {
    Spot spot = spotRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("관광지를 찾을 수 없습니다: " + id));
    return convertToDto(spot);
  }

  @Transactional
  public SpotDto updateSpot(Integer id, SpotUpdateDto updateDto) {
    Spot spot = spotRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("관광지를 찾을 수 없습니다: " + id));

    if (updateDto.getType() != null && !codeService.existsCode("030", updateDto.getType())) {
      throw new IllegalArgumentException("유효하지 않은 타입입니다: " + updateDto.getType());
    }

    if (updateDto.getRegionId() != null && !regionRepository.existsById(updateDto.getRegionId())) {
      throw new IllegalArgumentException("유효하지 않은 지역입니다: " + updateDto.getRegionId());
    }

    updateSpotFields(spot, updateDto);
    Spot updatedSpot = spotRepository.save(spot);
    return convertToDto(updatedSpot);
  }

  @Transactional
  public void deleteSpot(Integer id) {
    if (!spotRepository.existsById(id)) {
      throw new RuntimeException("관광지를 찾을 수 없습니다: " + id);
    }
    spotRepository.deleteById(id);
  }

  public List<SpotDto> getAllSpots() {
    return spotRepository.findAll()
            .stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
  }

  public List<SpotDto> getSpotsByRegion(Integer regionId) {
    return spotRepository.findByRegionIdOrderByIdDesc(regionId)
            .stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
  }

  public List<SpotDto> getSpotsByType(String type) {
    return spotRepository.findByTypeOrderByIdDesc(type)
            .stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
  }

  public List<SpotDto> getSpotsByRegionAndType(Integer regionId, String type) {
    return spotRepository.findByRegionIdAndTypeOrderByIdDesc(regionId, type)
            .stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
  }

  public List<SpotDto> searchSpotsByName(String spotName) {
    return spotRepository.findBySpotNameContainingIgnoreCaseOrderByIdDesc(spotName)
            .stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
  }

  public Page<SpotDto> getAllSpotsPaged(Pageable pageable) {
    return spotRepository.findAllByOrderByIdDesc(pageable)
            .map(this::convertToDto);
  }

  public Page<SpotDto> getSpotsByTypePaged(String type, Pageable pageable) {
    return spotRepository.findByTypeOrderByIdDesc(type, pageable)
            .map(this::convertToDto);
  }

  public Page<SpotDto> getSpotsByRegionPaged(Integer regionId, Pageable pageable) {
    return spotRepository.findByRegionIdOrderByIdDesc(regionId, pageable)
            .map(this::convertToDto);
  }

  private SpotDto convertToDto(Spot spot) {
    SpotDto dto = SpotDto.from(spot);

    if (spot.getType() != null) {
      try {
        CodeDto codeDto = codeService.getCode("030", spot.getType());
        dto.setTypeName(codeDto.getCodeName());
      } catch (Exception e) {
        dto.setTypeName(spot.getType());
      }
    }

    if (spot.getRegionId() != null) {
      regionRepository.findById(spot.getRegionId())
              .ifPresent(region -> dto.setRegionName(region.getName()));
    }

    return dto;
  }

  private void updateSpotFields(Spot spot, SpotUpdateDto updateDto) {
    if (updateDto.getRegionId() != null) spot.setRegionId(updateDto.getRegionId());
    if (updateDto.getSpotName() != null) spot.setSpotName(updateDto.getSpotName());
    if (updateDto.getAddress() != null) spot.setAddress(updateDto.getAddress());
    if (updateDto.getType() != null) spot.setType(updateDto.getType());
    if (updateDto.getImageUrl() != null) spot.setImageUrl(updateDto.getImageUrl());
    if (updateDto.getThumbnailUrl() != null) spot.setThumbnailUrl(updateDto.getThumbnailUrl());
  }

  private void validateSpotCreateDto(SpotCreateDto createDto) {
    if (createDto.getRegionId() == null) {
      throw new IllegalArgumentException("지역은 필수입니다");
    }
    if (createDto.getSpotName() == null || createDto.getSpotName().trim().isEmpty()) {
      throw new IllegalArgumentException("관광지명은 필수입니다");
    }
    if (createDto.getAddress() == null || createDto.getAddress().trim().isEmpty()) {
      throw new IllegalArgumentException("주소는 필수입니다");
    }
    if (createDto.getType() == null || createDto.getType().trim().isEmpty()) {
      throw new IllegalArgumentException("타입은 필수입니다");
    }
  }
}
