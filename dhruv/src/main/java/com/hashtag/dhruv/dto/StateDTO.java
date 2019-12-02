package com.hashtag.dhruv.dto;

public class StateDTO {
	private String stateCode;
	private String stateName;

	public StateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StateDTO(String stateCode, String stateName) {
		super();
		this.stateCode = stateCode;
		this.stateName = stateName;
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

}
