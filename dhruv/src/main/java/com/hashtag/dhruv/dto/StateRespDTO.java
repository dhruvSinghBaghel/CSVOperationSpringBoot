package com.hashtag.dhruv.dto;

public class StateRespDTO {
	private String state;
	private String district_code;
	private String district;

	public StateRespDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StateRespDTO(String state, String district_code, String district) {
		super();
		this.state = state;
		this.district_code = district_code;
		this.district = district;
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
		return "StateRespDTO [state=" + state + ", district_code=" + district_code + ", district=" + district + "]";
	}

}
