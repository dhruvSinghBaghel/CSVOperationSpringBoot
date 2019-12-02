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
import com.hashtag.dhruv.dto.DistrictDTO;
import com.hashtag.dhruv.listener.DistrictJobListener;
import com.hashtag.dhruv.processor.DistrictProcessor;

@Configuration
public class DistrictConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public FlatFileItemReader<CountryDTO> reader1() {
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
	public DistrictProcessor processor1() {
		return new DistrictProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<DistrictDTO> writer1() {
		JdbcBatchItemWriter<DistrictDTO> writer1 = new JdbcBatchItemWriter<DistrictDTO>();
		writer1.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		writer1.setSql("INSERT INTO district (district_code,district_name,urban_status,state_code) "
				+ "VALUES (:districtCode, :districtName,:urbanStatus,:stateCode)");
		writer1.setDataSource(dataSource);
		return writer1;
	}

	@Bean(name = "districtJob")
	public Job importUserJob1(DistrictJobListener listener) {
		return jobBuilderFactory.get("importUserJob1").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step2()).end().build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").<CountryDTO, DistrictDTO>chunk(10).reader(reader1())
				.processor(processor1()).writer(writer1()).build();
	}

}
