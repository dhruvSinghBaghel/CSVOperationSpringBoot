package com.hashtag.dhruv.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "district")
public class District implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8987781483801656186L;
	private long districtId;
	private String districtName;
	private Set<City> city;
	private State state;
	private String districtCode;
	private String urbanCode;

	public District() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}

	@Column(name = "district_name", unique = true)
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@ManyToOne
	@JoinColumn(name = "state_code", referencedColumnName = "state_code", updatable = true, columnDefinition = "varchar(255)")
	public State getState() {
		return state;
	}

	@OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
	public Set<City> getCity() {
		return city;
	}

	public void setCity(Set<City> city) {
		this.city = city;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Column(name = "district_code")
	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	@Column(name = "urban_status")
	public String getUrbanCode() {
		return urbanCode;
	}

	public void setUrbanCode(String urbanCode) {
		this.urbanCode = urbanCode;
	}

}
