package com.hashtag.dhruv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hashtag.dhruv.model.State;

@Repository
public interface StateRepository extends CrudRepository<State, Long> {

	State findByStateNameIgnoreCase(String state);
	State findByStateNameLike(String searchTerm);
	@Query("select c from State c where c.stateName like ?1")
    public List<State> findByStateNameLikeQuery(String searchTerm);
}
