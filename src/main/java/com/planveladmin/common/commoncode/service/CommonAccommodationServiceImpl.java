package com.planveladmin.common.commoncode.service;

import com.planveladmin.common.commoncode.dto.CommonAccommodationDto;
import com.planveladmin.common.commoncode.repository.CommonAccommodationRepository;
import com.planveladmin.domain.Accommodation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommonAccommodationServiceImpl implements CommonAccommodationService {

    private final CommonAccommodationRepository accommodationRepository;

    public List<CommonAccommodationDto> getAccommodations(String typeCode) {
        List<Accommodation> list = (typeCode == null || typeCode.isEmpty()) ?
                accommodationRepository.findAll() :
                accommodationRepository.findByTypeCode(typeCode);

        return list.stream()
                .map(a -> new CommonAccommodationDto(
                        a.getId(), a.getName(), a.getAddress(),
                        a.getPricePerNight(), a.getTypeCode()))
                .collect(Collectors.toList());
    }
}