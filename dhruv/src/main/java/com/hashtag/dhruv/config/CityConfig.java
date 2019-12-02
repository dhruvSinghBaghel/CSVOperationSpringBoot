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

import com.hashtag.dhruv.dto.CityDTO;
import com.hashtag.dhruv.dto.CountryDTO;
import com.hashtag.dhruv.listener.CityJobListener;
import com.hashtag.dhruv.processor.CityProcessor;

@Configuration
public class CityConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public FlatFileItemReader<CountryDTO> reader2() {
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
	public CityProcessor processor2() {
		return new CityProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<CityDTO> writer2() {
		JdbcBatchItemWriter<CityDTO> writer = new JdbcBatchItemWriter<CityDTO>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		writer.setSql("INSERT INTO city (city_name,district_id) " + "VALUES (:cityName, :districtCode)");
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean(name = "cityJob")
	public Job importUserJob(CityJobListener listener) {
		return jobBuilderFactory.get("importUserJob2").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step3()).end().build();
	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3").<CountryDTO, CityDTO>chunk(10).reader(reader2()).processor(processor2())
				.writer(writer2()).build();
	}

}
