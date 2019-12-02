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
public class StateJobListener extends JobExecutionListenerSupport {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StateJobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("In Completion Listener ..");
            List<StateDTO> results = jdbcTemplate.query("SELECT state_id,state_code,state_name from state",
                    (rs,rowNum)->{
                        return new StateDTO(rs.getString(1), rs.getString(2));
                    }
            );
            results.forEach(System.out::println);
        }
    }

}
