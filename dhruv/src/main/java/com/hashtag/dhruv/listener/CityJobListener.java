package com.hashtag.dhruv.listener;

import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hashtag.dhruv.dto.StateDTO;


@Component
public class CityJobListener extends JobExecutionListenerSupport {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CityJobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("In Completion Listener ..");
            List<StateDTO> results = jdbcTemplate.query("SELECT city_name, district_id from city",
                    (rs,rowNum)->{
                        return new StateDTO(rs.getString(1), rs.getString(2));
                    }
            );
            results.forEach(System.out::println);
        }
    }

}
