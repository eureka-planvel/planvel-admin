package com.planveladmin.controller;

import com.planveladmin.domain.Spot;
import com.planveladmin.dto.CommonResponse;
import com.planveladmin.service.SpotService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/spot")
@RequiredArgsConstructor
public class SpotController {
  private final SpotService spotService;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<CommonResponse<Void>> registerSpot(
      @RequestParam("regionId") Integer regionId,
      @RequestParam("spotName") String spotName,
      @RequestParam("address") String address,
      @RequestParam(value = "type", required = false) String type,
      @RequestPart("image") MultipartFile image
  ) {
    return ResponseEntity.ok(
        spotService.register(regionId, spotName, address, type, image)
    );
  }

  @GetMapping
  public ResponseEntity<CommonResponse<List<Spot>>> getAll() {
    return ResponseEntity.ok(spotService.getAll());
  }

  @GetMapping("/region/{regionId}")
  public ResponseEntity<CommonResponse<List<Spot>>> getByRegion(@PathVariable int regionId) {
    return ResponseEntity.ok(spotService.getByRegion(regionId));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CommonResponse<Spot>> getById(@PathVariable int id) {
    return ResponseEntity.ok(spotService.getById(id));
  }


}
