package com.hashtag.dhruv.dto;

public class CityRespDTO {
	private String town;
	private String state;
	private String district;

	public CityRespDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CityRespDTO(String town, String state, String district) {
		super();
		this.town = town;
		this.state = state;
		this.district = district;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Override
	public String toString() {
		return "CityRespDTO [town=" + town + ", state=" + state + ", district=" + district + "]";
	}

}
