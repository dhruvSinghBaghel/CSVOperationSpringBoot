package com.hashtag.dhruv.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.hashtag.dhruv.dto.CountryDTO;
import com.hashtag.dhruv.dto.StateDTO;
import com.hashtag.dhruv.listener.StateJobListener;
import com.hashtag.dhruv.processor.StateProcessor;

@Configuration
public class StateConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public FlatFileItemReader<CountryDTO> reader() {
		FlatFileItemReader<CountryDTO> reader = new FlatFileItemReader<CountryDTO>();
		reader.setResource(new ClassPathResource("country.csv"));
		reader.setLinesToSkip(1);
		reader.setLineMapper(new DefaultLineMapper<CountryDTO>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "cityName", "urbanStatus", "stateCode", "stateName", "districtCode",
								"districtName" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper() {
					{
						setTargetType(CountryDTO.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public StateProcessor processor() {
		return new StateProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<StateDTO> writer() {
		JdbcBatchItemWriter<StateDTO> writer = new JdbcBatchItemWriter<StateDTO>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		writer.setSql("INSERT INTO state (state_code,state_name) " + "VALUES (:stateCode, :stateName)");
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean(name = "stateJob")
	public Job importUserJob(StateJobListener listener) {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<CountryDTO, StateDTO>chunk(10).reader(reader()).processor(processor())
				.writer(writer()).build();
	}

}
