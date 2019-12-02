package com.hashtag.dhruv.dto;

public class CityDTO {
	private String cityName;
	private String districtCode;

	public CityDTO() {
		super();
	}

	public CityDTO(String cityName, String districCode) {
		super();
		this.cityName = cityName;
		this.districtCode = districCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}


}
