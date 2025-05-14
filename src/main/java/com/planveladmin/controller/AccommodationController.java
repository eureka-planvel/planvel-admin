package com.planveladmin.controller;

import com.planveladmin.domain.Accommodation;
import com.planveladmin.dto.CommonResponse;
import com.planveladmin.dto.request.AccommodationRequestDto;
import com.planveladmin.service.AccommodationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/accommodation")
@RequiredArgsConstructor
public class AccommodationController {

  private final AccommodationService accommodationService;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<CommonResponse<Void>> registerAccommodation(
      @RequestParam("regionId") Integer regionId,
      @RequestParam("name") String name,
      @RequestParam("address") String address,
      @RequestParam("pricePerNight") Integer pricePerNight,
      @RequestParam("isHotel") Boolean isHotel,
      @RequestPart("image") MultipartFile image
  ) {
    return ResponseEntity.ok(
        accommodationService.register(regionId, name, address, pricePerNight, isHotel, image)
    );
  }

  @GetMapping
  public ResponseEntity<CommonResponse<List<Accommodation>>> getAll() {
    return ResponseEntity.ok(accommodationService.getAll());
  }

  @GetMapping("/region/{regionId}")
  public ResponseEntity<CommonResponse<List<Accommodation>>> getByRegion(@PathVariable int regionId) {
    return ResponseEntity.ok(accommodationService.getByRegion(regionId));
  }


  @GetMapping("/{id}")
  public ResponseEntity<CommonResponse<Accommodation>> getById(@PathVariable int id) {
    return ResponseEntity.ok(accommodationService.getById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CommonResponse<Void>> updateAccommodation(@PathVariable int id, @RequestBody AccommodationRequestDto request) {
    return ResponseEntity.ok(accommodationService.update(id, request));
  }

}
