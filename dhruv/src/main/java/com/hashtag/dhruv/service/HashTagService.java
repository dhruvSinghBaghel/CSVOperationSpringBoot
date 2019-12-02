package com.hashtag.dhruv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashtag.dhruv.dto.CityRespDTO;
import com.hashtag.dhruv.dto.DistrictRespDTO;
import com.hashtag.dhruv.dto.StateRespDTO;
import com.hashtag.dhruv.model.City;
import com.hashtag.dhruv.model.District;
import com.hashtag.dhruv.model.State;
import com.hashtag.dhruv.repository.CityRepository;
import com.hashtag.dhruv.repository.DistrictRepository;
import com.hashtag.dhruv.repository.StateRepository;

@Service
public class HashTagService {

	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	

	@Transactional
	public List<StateRespDTO> getStateDistrict(String inputState) {
		State state = stateRepository.findByStateNameIgnoreCase(inputState.trim());
		if (null == state) {

			throw new RuntimeException("Invalid State Name provided in request");
		}
		List<District> districts = districtRepository.findByState(state);
		List<StateRespDTO> stateRespDTO = districts.stream()
				.map(s -> new StateRespDTO(s.getState().getStateName(), s.getDistrictCode(), s.getDistrictName()))
				.collect(Collectors.toList());

		return stateRespDTO;
	}

	public List<DistrictRespDTO> getDistrictDetails(String inputDistrict) {
		District district = districtRepository.findByDistrictNameIgnoreCase(inputDistrict);
		if (null == district) {
			throw new RuntimeException("Invalid District Name provided in request");
		}
		List<City> cities = cityRepository.findByDistrict(district);
		List<DistrictRespDTO> districtRespDTO = cities.stream()
				.map(s -> new DistrictRespDTO(s.getCityName(), s.getDistrict().getUrbanCode(),
						s.getDistrict().getState().getStateCode(), s.getDistrict().getState().getStateName(),
						s.getDistrict().getDistrictCode(), s.getDistrict().getDistrictName()))
				.collect(Collectors.toList());
		return districtRespDTO;
	}

	public List<CityRespDTO> getCityDetails(String town) {
		List<CityRespDTO> cityRespDTOs = new ArrayList<CityRespDTO>();

		List<City> cities = cityRepository.findByCityNameIgnoreCase(town);
		City city =null != cities? cities.get(0):null;
		if (null == city) {
			throw new RuntimeException("Invalid City Name provided in request");
		}

		CityRespDTO cityRespDTO = new CityRespDTO(city.getCityName(), city.getDistrict().getState().getStateName(),
				city.getDistrict().getDistrictName());
		cityRespDTOs.add(cityRespDTO);

		return cityRespDTOs;

	}

}
