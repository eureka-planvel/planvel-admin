package com.planveladmin.common.commoncode.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.planveladmin.common.commoncode.dto.CodeResultDto;
import com.planveladmin.common.commoncode.dto.GroupCodeDto;
import com.planveladmin.common.commoncode.entity.GroupCode;
import com.planveladmin.common.commoncode.repository.GroupCodeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupCodeServiceImpl implements GroupCodeService{

	private final GroupCodeRepository groupCodeRepository;
	
	@Override
	public CodeResultDto insertGroupCode(GroupCode groupCode) {
		CodeResultDto codeResultDto = new CodeResultDto();
		try {
			groupCode.setNew(true);
			groupCodeRepository.save(groupCode);
			codeResultDto.setResult("success");
		} catch(Exception e) {
			e.printStackTrace();
			codeResultDto.setResult("fail");
		}
		return codeResultDto;
	}

	@Override
	public CodeResultDto updateGroupCode(GroupCode groupCode) {
		CodeResultDto codeResultDto = new CodeResultDto();
		try {
			groupCode.setNew(false);
			groupCodeRepository.save(groupCode);
			codeResultDto.setResult("success");
		} catch(Exception e) {
			e.printStackTrace();
			codeResultDto.setResult("fail");
		}
		return codeResultDto;
	}

	@Override
	public CodeResultDto deleteGroupCode(String groupCode) {
		CodeResultDto codeResultDto = new CodeResultDto();
		try {
			groupCodeRepository.deleteById(groupCode);
			codeResultDto.setResult("success");
		} catch(Exception e) {
			e.printStackTrace();
			codeResultDto.setResult("fail");
		}
		return codeResultDto;
	}

	@Override
	public CodeResultDto listGroupCode(int pageNumber, int pageSize) {
		CodeResultDto codeResultDto = new CodeResultDto();
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<GroupCode> page = groupCodeRepository.findAll(pageable);
			List<GroupCodeDto> groupCodeDtoList = new ArrayList<>();

			page.toList().forEach(groupCode -> groupCodeDtoList.add(GroupCodeDto.fromGroupCode(groupCode)));
			codeResultDto.setGroupCodeDtoList(groupCodeDtoList);

			Long count = groupCodeRepository.count();
			codeResultDto.setCount(count);
			codeResultDto.setResult("success");
		} catch(Exception e) {
			e.printStackTrace();
			codeResultDto.setResult("fail");
		}
		return codeResultDto;
	}

	@Override
	public CodeResultDto detailGroupCode(String groupCode) {
		CodeResultDto codeResultDto = new CodeResultDto();
		try {
			Optional<GroupCode> optionalGroupCode = groupCodeRepository.findById(groupCode);
			
			optionalGroupCode.ifPresentOrElse(
					detailGroupCode -> {
						codeResultDto.setGroupCodeDto(GroupCodeDto.fromGroupCode(detailGroupCode));
						codeResultDto.setResult("success");
					},
					() -> codeResultDto.setResult("fail")
			);
		} catch(Exception e) {
			e.printStackTrace();
			codeResultDto.setResult("fail");
		}
		return codeResultDto;
	}

	@Override
	public CodeResultDto countGroupCode() {
		CodeResultDto codeResultDto = new CodeResultDto();
		try {
			Long count = groupCodeRepository.count();
			codeResultDto.setCount(count);
			codeResultDto.setResult("success");
		}catch(Exception e) {
			e.printStackTrace();
			codeResultDto.setResult("fail");
		}
		
		return codeResultDto;
	}

}
