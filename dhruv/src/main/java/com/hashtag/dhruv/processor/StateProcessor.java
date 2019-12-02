package com.hashtag.dhruv.processor;

import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;

import com.hashtag.dhruv.dto.CountryDTO;
import com.hashtag.dhruv.dto.StateDTO;

public class StateProcessor implements ItemProcessor<CountryDTO, StateDTO> {
	private Set<String> stateCode = new HashSet<String>();
    @Override
    public StateDTO process(final CountryDTO employee) throws Exception {
        System.out.println("Transforming CountryDTO(s) to StateDTO(s)..");
        final StateDTO empployeeDto = new StateDTO(employee.getStateCode(), employee.getStateName());
        if(stateCode.contains(empployeeDto.getStateCode())) {
        	return null;
        }
        stateCode.add(empployeeDto.getStateCode());
        return empployeeDto;
    }
    
}
