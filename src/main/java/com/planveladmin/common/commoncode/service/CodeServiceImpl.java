package com.planveladmin.common.commoncode.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.planveladmin.common.commoncode.dto.CodeDto;
import com.planveladmin.common.commoncode.dto.CodeResultDto;
import com.planveladmin.common.commoncode.entity.Code;
import com.planveladmin.common.commoncode.entity.key.CodeKey;
import com.planveladmin.common.commoncode.repository.CodeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService{

	private final CodeRepository codeRepository;
	
	@Override
	public CodeResultDto insertCode(Code code) {
		CodeResultDto codeResultDto = new CodeResultDto();
		try {
			codeRepository.save(code);
			codeResultDto.setResult("success");
		} catch(Exception e) {
			e.printStackTrace();
			codeResultDto.setResult("fail");
		}
		return codeResultDto;
	}

	@Override
	public CodeResultDto updateCode(Code code) {
		CodeResultDto codeResultDto = new CodeResultDto();
		try {
			codeRepository.save(code);
			codeResultDto.setResult("success");
		} catch(Exception e) {
			e.printStackTrace();
			codeResultDto.setResult("fail");
		}
		return codeResultDto;
	}

	@Override
	public CodeResultDto deleteCode(CodeKey codeKey) {
		CodeResultDto codeResultDto = new CodeResultDto();
		try {
			codeRepository.deleteById(codeKey);
			codeResultDto.setResult("success");
		} catch(Exception e) {
			e.printStackTrace();
			codeResultDto.setResult("fail");
		}
		return codeResultDto;
	}

	@Override
	public CodeResultDto listCode(String groupCode, int pageNumber, int pageSize) {
		CodeResultDto codeResultDto = new CodeResultDto();
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			Page<Code> page = codeRepository.findByGroupCode(groupCode, pageable);
			List<CodeDto> codeDtoList = new ArrayList<>();

			page.toList().forEach(code -> codeDtoList.add(CodeDto.fromCode(code)));
			codeResultDto.setCodeDtoList(codeDtoList);

			Long count = codeRepository.count();
			codeResultDto.setCount(count);
			codeResultDto.setResult("success");
		} catch(Exception e) {
			e.printStackTrace();
			codeResultDto.setResult("fail");
		}
		return codeResultDto;
	}

	@Override
	public CodeResultDto detailCode(CodeKey codeKey) {
		CodeResultDto codeResultDto = new CodeResultDto();
		try {
			Optional<Code> optionalCode = codeRepository.findById(codeKey);
			
			optionalCode.ifPresentOrElse(
					detailCode -> {
						codeResultDto.setCodeDto(CodeDto.fromCode(detailCode));
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
	public CodeResultDto countCode() {
		CodeResultDto codeResultDto = new CodeResultDto();
		try {
			Long count = codeRepository.count();
			codeResultDto.setCount(count);
			codeResultDto.setResult("success");
		}catch(Exception e) {
			e.printStackTrace();
			codeResultDto.setResult("fail");
		}
		
		return codeResultDto;
	}

}
