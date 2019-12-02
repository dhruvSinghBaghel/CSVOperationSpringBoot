package com.hashtag.dhruv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8633555291192436876L;
	private long cityId;
	private String cityName;
	private District district;
	

	public City() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	@Column(name = "city_name", nullable = false)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@ManyToOne
	@JoinColumn(name="districtId", updatable=true, columnDefinition="varchar(255)")
	public District getDistrict() {
		return district;
	}


	public void setDistrict(District district) {
		this.district = district;
	}
	

}
