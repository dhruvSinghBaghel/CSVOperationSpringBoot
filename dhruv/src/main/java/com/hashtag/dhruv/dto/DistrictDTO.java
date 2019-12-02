package com.hashtag.dhruv.dto;

public class DistrictDTO {
	private String districtCode;
	private String districtName;
	private String urbanStatus;
	private String stateCode;

	public DistrictDTO() {
		// TODO Auto-generated constructor stub
	}

	public DistrictDTO(String districtCode, String districtName, String urbanStatus, String stateCode) {
		super();
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.urbanStatus = urbanStatus;
		this.stateCode = stateCode;
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

}
