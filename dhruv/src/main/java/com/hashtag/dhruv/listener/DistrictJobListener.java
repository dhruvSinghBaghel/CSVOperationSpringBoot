package com.hashtag.dhruv.listener;

import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hashtag.dhruv.dto.DistrictDTO;


@Component
public class DistrictJobListener extends JobExecutionListenerSupport {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DistrictJobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("In Completion Listener ..");
            List<DistrictDTO> results = jdbcTemplate.query("SELECT district_code,district_name,urban_status,state_code from district",
                    (rs,rowNum)->{
                        return new DistrictDTO(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4));
                    }
            );
            results.forEach(System.out::println);
        }
    }

}
