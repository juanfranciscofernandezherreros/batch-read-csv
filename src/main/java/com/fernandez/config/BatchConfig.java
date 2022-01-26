package com.fernandez.config;

import com.fernandez.dto.Product;
import com.fernandez.dto.ProductDTO;
import com.fernandez.processor.MyCustomProcessor;
import com.fernandez.reader.MyCustomReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fernandez.writer.MyCustomWriter;

import java.io.Writer;

@Configuration
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job(Step step) {
		Job job = jobBuilderFactory.get("job1")
				.flow(step)
				.end()
				.build();
		return job;
	}

	@Bean
	public Step step() {

		TaskletStep step = stepBuilderFactory.get("step1")
				.<ProductDTO,Product>chunk(1)
				.reader(new MyCustomReader())
				.processor(new MyCustomProcessor())
				.writer(new MyCustomWriter())
				.build();
		return step;
	}


}
