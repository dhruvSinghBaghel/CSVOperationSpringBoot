package com.hashtag.dhruv;

import javax.batch.operations.JobRestartException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableBatchProcessing
public class DhruvApplication {

	public static void main(String[] args)
			throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, org.springframework.batch.core.repository.JobRestartException {

		ConfigurableApplicationContext ctx = SpringApplication.run(DhruvApplication.class, args);
		JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");
		Job job1 = (Job) ctx.getBean("stateJob");
		Job job2 = (Job) ctx.getBean("districtJob");
		Job job3 = (Job) ctx.getBean("cityJob");
		//Run Spring batch job in order
		jobLauncher.run(job1, new JobParameters());
		jobLauncher.run(job2, new JobParameters());
		jobLauncher.run(job3, new JobParameters());

	}

}
