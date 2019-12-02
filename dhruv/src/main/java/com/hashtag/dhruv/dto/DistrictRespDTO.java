package com.hashtag.dhruv.dto;

public class DistrictRespDTO {
	private String town;
	private String urban_status;
	private String state_code;
	private String state;
	private String district_code;
	private String district;

	public DistrictRespDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DistrictRespDTO(String town, String urban_status, String state_code, String state, String district_code,
			String district) {
		super();
		this.town = town;
		this.urban_status = urban_status;
		this.state_code = state_code;
		this.state = state;
		this.district_code = district_code;
		this.district = district;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getUrban_status() {
		return urban_status;
	}

	public void setUrban_status(String urban_status) {
		this.urban_status = urban_status;
	}

	public String getState_code() {
		return state_code;
	}

	public void setState_code(String state_code) {
		this.state_code = state_code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Override
	public String toString() {
		return "DistrictRespDTO [town=" + town + ", urban_status=" + urban_status + ", state_code=" + state_code
				+ ", state=" + state + ", district_code=" + district_code + ", district=" + district + "]";
	}

}
