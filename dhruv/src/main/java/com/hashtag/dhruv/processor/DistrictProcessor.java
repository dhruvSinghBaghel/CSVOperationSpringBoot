package com.hashtag.dhruv.processor;

import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.hashtag.dhruv.dto.CountryDTO;
import com.hashtag.dhruv.dto.DistrictDTO;
import com.hashtag.dhruv.repository.StateRepository;

public class DistrictProcessor implements ItemProcessor<CountryDTO, DistrictDTO> {

	@Autowired
	StateRepository stateRepository;
	private Set<String> districtName = new HashSet<String>();
	@Override
	public DistrictDTO process(final CountryDTO employee) throws Exception {
		System.out.println("Transforming CountryDTO(s) to DistrictDTO(s)..");
		final DistrictDTO empployeeDto = new DistrictDTO(employee.getDistrictCode(), employee.getDistrictName(),
				employee.getUrbanStatus(), employee.getStateCode());
		  if(districtName.contains(empployeeDto.getDistrictName())) {
	        	return null;
	        }
		  districtName.add(empployeeDto.getDistrictName());

		return empployeeDto;
	}
}
