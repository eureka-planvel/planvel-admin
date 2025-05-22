package com.planveladmin.controller;

import com.planveladmin.domain.Spot;
import com.planveladmin.dto.CommonResponse;
import com.planveladmin.dto.SpotCreateDto;
import com.planveladmin.dto.SpotDto;
import com.planveladmin.dto.SpotUpdateDto;
import com.planveladmin.service.SpotService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/spots")
@RequiredArgsConstructor
@Validated
public class SpotController {

  private final SpotService spotService;

  @PostMapping
  public ResponseEntity<SpotDto> createSpot(@RequestBody SpotCreateDto createDto) {
    SpotDto spotDto = spotService.createSpot(createDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(spotDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SpotDto> getSpot(@PathVariable Integer id) {
    SpotDto spotDto = spotService.getSpot(id);
    return ResponseEntity.ok(spotDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SpotDto> updateSpot(@PathVariable Integer id,
                                            @RequestBody SpotUpdateDto updateDto) {
    SpotDto spotDto = spotService.updateSpot(id, updateDto);
    return ResponseEntity.ok(spotDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSpot(@PathVariable Integer id) {
    spotService.deleteSpot(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<SpotDto>> getAllSpots() {
    List<SpotDto> spots = spotService.getAllSpots();
    return ResponseEntity.ok(spots);
  }

  @GetMapping("/region/{regionId}")
  public ResponseEntity<List<SpotDto>> getSpotsByRegion(@PathVariable Integer regionId) {
    List<SpotDto> spots = spotService.getSpotsByRegion(regionId);
    return ResponseEntity.ok(spots);
  }

  @GetMapping("/type/{type}")
  public ResponseEntity<List<SpotDto>> getSpotsByType(@PathVariable String type) {
    List<SpotDto> spots = spotService.getSpotsByType(type);
    return ResponseEntity.ok(spots);
  }

  @GetMapping("/region/{regionId}/type/{type}")
  public ResponseEntity<List<SpotDto>> getSpotsByRegionAndType(@PathVariable Integer regionId,
                                                               @PathVariable String type) {
    List<SpotDto> spots = spotService.getSpotsByRegionAndType(regionId, type);
    return ResponseEntity.ok(spots);
  }

  @GetMapping("/search")
  public ResponseEntity<List<SpotDto>> searchSpots(@RequestParam String name) {
    List<SpotDto> spots = spotService.searchSpotsByName(name);
    return ResponseEntity.ok(spots);
  }

  @GetMapping("/paged")
  public ResponseEntity<Page<SpotDto>> getSpotsPaged(
          @RequestParam(required = false) String type,
          @RequestParam(required = false) Integer regionId,
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "10") int size) {

    Pageable pageable = PageRequest.of(page, size);
    Page<SpotDto> spots;

    if (type != null && !type.isEmpty()) {
      spots = spotService.getSpotsByTypePaged(type, pageable);
    } else if (regionId != null) {
      spots = spotService.getSpotsByRegionPaged(regionId, pageable);
    } else {
      spots = spotService.getAllSpotsPaged(pageable);
    }

    return ResponseEntity.ok(spots);
  }
}