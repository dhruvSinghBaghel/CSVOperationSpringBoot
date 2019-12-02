package com.hashtag.dhruv.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "state")
public class State implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -966799989021007892L;
	private long stateId;
	private String stateName;
	private Set<District> district;
	private String stateCode;
	public State() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	@Column(name = "state_name", unique=true)
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Set<District> getDistrict() {
		return district;
	}

	public void setDistrict(Set<District> district) {
		this.district = district;
	}
	@Column(name="state_code", unique=true, updatable=true)
	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

}
