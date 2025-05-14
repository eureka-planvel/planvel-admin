package com.planveladmin.service;

import com.planveladmin.domain.Spot;
import com.planveladmin.domain.type.SpotType;
import com.planveladmin.dto.CommonResponse;
import com.planveladmin.mapper.SpotMapper;
import java.io.File;
import java.io.IOException;
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
public class SpotService {

  private final SpotMapper spotMapper;

  public CommonResponse<Void> register(Integer regionId, String spotName, String address, String type, MultipartFile image) {

    SpotType spotType = SpotType.fromString(type);

    Map<String, String> imageUrls = saveImageWithThumbnail(image);

    Spot spot = new Spot();
    spot.setRegionId(regionId);
    spot.setSpotName(spotName);
    spot.setAddress(address);
    spot.setType(spotType.name());
    spot.setImageUrl(imageUrls.get("original"));
    spot.setThumbnailUrl(imageUrls.get("thumbnail"));

    int result = spotMapper.insertSpot(spot);
    if (result == 0) {
      return CommonResponse.fail("스팟 등록 실패");
    }
    return CommonResponse.success("스팟 등록 성공");
  }

  private Map<String, String> saveImageWithThumbnail(MultipartFile image) {
    Map<String, String> imageUrls = new HashMap<>();

    String originalFilename = image.getOriginalFilename();
    String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
    String uniqueFileName = UUID.randomUUID().toString() + extension;

    String uploadDir = "/uploads/spot/";
    String originalDir = uploadDir + "original/";
    String thumbnailDir = uploadDir + "thumbnail/";

    String originalFilePath = originalDir + uniqueFileName;
    String thumbnailFilePath = thumbnailDir + uniqueFileName;

    try {
      new File(originalDir).mkdirs();
      new File(thumbnailDir).mkdirs();

      File originalFile = new File(originalFilePath);
      image.transferTo(originalFile);

      Thumbnails.of(originalFile)
          .size(200, 200)
          .crop(Positions.CENTER)
          .toFile(new File(thumbnailFilePath));

      imageUrls.put("original", "/uploads/spot/original/" + uniqueFileName);
      imageUrls.put("thumbnail", "/uploads/spot/thumbnail/" + uniqueFileName);

      return imageUrls;
    } catch (IOException e) {
      System.err.println("스팟 이미지 처리 실패: " + e.getMessage());
      e.printStackTrace();
      throw new RuntimeException("스팟 이미지 처리 실패", e);
    }
  }

  public CommonResponse<List<Spot>> getAll() {
    return CommonResponse.success(spotMapper.selectAll(), "전체 스팟 조회 성공");
  }

  public CommonResponse<List<Spot>> getByRegion(int regionId) {
    return CommonResponse.success(spotMapper.selectByRegion(regionId), "지역별 스팟 조회 성공");
  }

  public CommonResponse<Spot> getById(int id) {
    return CommonResponse.success(spotMapper.selectById(id), "스팟 상세 조회 성공");
  }
}
