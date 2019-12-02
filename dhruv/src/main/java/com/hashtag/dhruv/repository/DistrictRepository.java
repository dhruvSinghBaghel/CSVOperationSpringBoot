package com.hashtag.dhruv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hashtag.dhruv.model.District;
import com.hashtag.dhruv.model.State;

@Repository
public interface DistrictRepository extends CrudRepository<District, Long> {

	/*
	 * @Query("SELECT d.districtCode FROM District d where d.state = :stateCode")
	 * List<StateRespDTO> findDistrictByStateCode(@Param("stateCode")String
	 * stateCode);
	 */
	List<District> findByState(State state);
	District findByDistrictNameIgnoreCase(String district);
	//District findByDistrictCodeAndState(String districtCode,String stateCode);
}
