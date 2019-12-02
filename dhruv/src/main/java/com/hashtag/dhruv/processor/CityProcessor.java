package com.hashtag.dhruv.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.hashtag.dhruv.dto.CityDTO;
import com.hashtag.dhruv.dto.CountryDTO;
import com.hashtag.dhruv.model.District;
import com.hashtag.dhruv.repository.DistrictRepository;

public class CityProcessor implements ItemProcessor<CountryDTO, CityDTO> {
	@Autowired
	DistrictRepository districtRepository;

	@Override
	public CityDTO process(final CountryDTO employee) throws Exception {
		System.out.println("Transforming CountryDTO(s) to CityDTO(s)..");
		District district = districtRepository.findByDistrictNameIgnoreCase(employee.getDistrictName());
		final CityDTO empployeeDto = new CityDTO(employee.getCityName(),
				String.valueOf(null != district ? district.getDistrictId() : ""));

		return empployeeDto;
	}
}
