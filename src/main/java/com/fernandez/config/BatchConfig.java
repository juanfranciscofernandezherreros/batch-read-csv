package com.fernandez.config;

import com.fernandez.dto.ProductDTO;
import com.fernandez.entity.Product;
import com.fernandez.listeners.JobCompletionListener;
import com.fernandez.processor.Processor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fernandez.writer.Writer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class BatchConfig {

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job helloWorldJob(){
		return jobBuilderFactory.get("readJSON")
				.incrementer(new RunIdIncrementer())
				.start(step2())
				.build();
	}


	@Bean
	public Step step2()  {
		return stepBuilderFactory
				.get("movieStep")
				.<ProductDTO, Product>chunk(10)
				.reader(jsonItemReader(null))
				.processor(new Processor())
				.writer(new Writer())
				.build();
	}

	@StepScope
	@Bean
	public JsonItemReader jsonItemReader(
			@Value( "#{jobParameters['fileInput']}" ) FileSystemResource inputFile){
		JsonItemReader reader = new JsonItemReader(inputFile, new JacksonJsonObjectReader(ProductDTO.class));
 		return reader;
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}



}
