package com.hashtag.dhruv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hashtag.dhruv.dto.CityRespDTO;
import com.hashtag.dhruv.dto.DistrictRespDTO;
import com.hashtag.dhruv.dto.StateRespDTO;
import com.hashtag.dhruv.service.HashTagService;


@RestController
public class HashTagController {

	@Autowired
	HashTagService hashTagService;

	@GetMapping("/state")
	@ResponseBody
	public List<StateRespDTO> getStateDistrict(@RequestParam("q") String state) {

		List<StateRespDTO> stateDetails = hashTagService.getStateDistrict(state);
		return stateDetails;
	}

	@GetMapping("/district")
	@ResponseBody
	public List<DistrictRespDTO> getDistrictDetails(@RequestParam("q") String district) {

		List<DistrictRespDTO> districtDetails = hashTagService.getDistrictDetails(district);
		return districtDetails;
	}

	@GetMapping("/town")
	@ResponseBody
	public List<CityRespDTO> getCityDetails(@RequestParam("q") String town) {

		List<CityRespDTO> cityRespDTOs = hashTagService.getCityDetails(town);
		return cityRespDTOs;
	}
}
