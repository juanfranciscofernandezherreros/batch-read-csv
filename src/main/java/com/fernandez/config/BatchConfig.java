package com.fernandez.config;

import com.fernandez.dto.MatchDTO;
import com.fernandez.listeners.JobCompletionListener;
import com.fernandez.listeners.ProcessListener;
import com.fernandez.listeners.ReaderListener;
import com.fernandez.listeners.WriterListener;
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
import com.fernandez.processor.Processor;
import com.fernandez.reader.Reader;
import com.fernandez.writer.Writer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory steps;

	@Bean
	public Job helloWorldJob(){
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer())
				.start(step2())
				.build();
	}

	@Bean
	public Step step2(){
		return steps.get("step2").
				<Integer,Integer>chunk(3)
				.reader(jsonItemReader(null))
				.writer(new Writer())
				.build();
	}

	@StepScope
	@Bean
	public JsonItemReader jsonItemReader(
			@Value( "#{jobParameters['fileInput']}" ) FileSystemResource inputFile){
		JsonItemReader reader = new JsonItemReader(inputFile, new JacksonJsonObjectReader(MatchDTO.class));
		return reader;
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}

}
