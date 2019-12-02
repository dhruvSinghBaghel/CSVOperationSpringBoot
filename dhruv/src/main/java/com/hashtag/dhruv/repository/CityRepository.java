package com.hashtag.dhruv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hashtag.dhruv.model.City;
import com.hashtag.dhruv.model.District;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

	List<City> findByDistrict(District district);

	List<City> findByCityNameIgnoreCase(String town);
}
