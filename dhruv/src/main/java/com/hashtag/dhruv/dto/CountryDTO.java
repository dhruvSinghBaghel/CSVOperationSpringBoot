package com.hashtag.dhruv.dto;

import java.io.Serializable;

public class CountryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9004649160514895612L;
	private String cityName;
	private String districtCode;
	private String districtName;
	private String urbanStatus;
	private String stateCode;
	private String stateName;


	public CountryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public CountryDTO(String cityName, String districtCode, String districtName, String urbanStatus, String stateCode,
			String stateName) {
		this.cityName = cityName;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.urbanStatus = urbanStatus;
		this.stateCode = stateCode;
		this.stateName = stateName;
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

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getUrbanStatus() {
		return urbanStatus;
	}

	public void setUrbanStatus(String urbanStatus) {
		this.urbanStatus = urbanStatus;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CountryDTO [cityName=" + cityName + ", districtCode=" + districtCode + ", districtName=" + districtName
				+ ", urbanStatus=" + urbanStatus + ", stateCode=" + stateCode + ", stateName=" + stateName + "]";
	}

}
