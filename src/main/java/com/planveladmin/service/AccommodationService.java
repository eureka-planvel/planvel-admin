package com.planveladmin.service;

import com.planveladmin.domain.Accommodation;
import com.planveladmin.dto.CommonResponse;
import com.planveladmin.dto.request.AccommodationRequestDto;
import com.planveladmin.mapper.AccommodationMapper;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AccommodationService {

  private final AccommodationMapper accommodationMapper;

  public CommonResponse<Void> register(Integer regionId, String name, String address, Integer pricePerNightInt, Boolean isHotel, MultipartFile image) {
    Map<String, String> imageUrls = saveImageWithThumbnail(image);

    Accommodation accommodation = new Accommodation();
    accommodation.setRegionId(regionId);
    accommodation.setName(name);
    accommodation.setAddress(address);
    accommodation.setPricePerNight(BigDecimal.valueOf(pricePerNightInt));
    accommodation.setIsHotel(isHotel);
    accommodation.setImageUrl(imageUrls.get("original"));
    accommodation.setThumbnailUrl(imageUrls.get("thumbnail"));

    int result = accommodationMapper.insertAccommodation(accommodation);
    if (result == 0) {
      return CommonResponse.fail("숙소 등록 실패");
    }
    return CommonResponse.success("숙소 등록 성공");
  }

  private Map<String, String> saveImageWithThumbnail(MultipartFile image) {
    Map<String, String> imageUrls = new HashMap<>();

    // 파일 이름 생성 (UUID + 확장자)
    String originalFilename = image.getOriginalFilename();
    String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
    String uniqueFileName = UUID.randomUUID().toString() + extension;

    // 경로 설정
    String uploadDir = "/uploads/accommodation/";
    String originalDir = uploadDir + "original/";
    String thumbnailDir = uploadDir + "thumbnail/";

    // 파일 경로
    String originalFilePath = originalDir + uniqueFileName;
    String thumbnailFilePath = thumbnailDir + uniqueFileName;

    try {
      // 디렉토리 생성
      new File(originalDir).mkdirs();
      new File(thumbnailDir).mkdirs();

      // 원본 이미지 저장
      File originalFile = new File(originalFilePath);
      image.transferTo(originalFile);

      // 썸네일 생성 (200x200 크기, 중앙에서 잘라내기)
      Thumbnails.of(originalFile)
          .size(200, 200)
          .crop(Positions.CENTER)
          .toFile(new File(thumbnailFilePath));

      // URL 반환
      imageUrls.put("original", "/uploads/accommodation/original/" + uniqueFileName);
      imageUrls.put("thumbnail", "/uploads/accommodation/thumbnail/" + uniqueFileName);

      return imageUrls;
    } catch (IOException e) {
      System.err.println("이미지 처리 실패: " + e.getMessage());
      e.printStackTrace();
      throw new RuntimeException("이미지 처리 실패", e);
    }
  }


  public CommonResponse<List<Accommodation>> getAll() {
    return CommonResponse.success(accommodationMapper.selectAll(), "전체 조회 성공");
  }

  public CommonResponse<List<Accommodation>> getByRegion(int regionId) {
    return CommonResponse.success(accommodationMapper.selectByRegion(regionId), "지역별 조회 성공");
  }

  public CommonResponse<Accommodation> getById(int id) {
    return CommonResponse.success(accommodationMapper.selectById(id), "상세 조회 성공");
  }

  public CommonResponse<Void> update(int id, AccommodationRequestDto request) {
    int result = accommodationMapper.updateAccommodation(id,request);
    if (result == 0) {
      return CommonResponse.fail("숙소 수정 실패");
    }
    return CommonResponse.success("숙소 수정 성공");
  }
}
