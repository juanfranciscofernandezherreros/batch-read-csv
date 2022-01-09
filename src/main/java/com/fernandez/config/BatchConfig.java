package com.fernandez.config;

import com.fernandez.dto.ProductDTO;
import com.fernandez.entity.Product;
import com.fernandez.listeners.*;
import com.fernandez.processor.Processor;
import com.fernandez.reader.Reader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fernandez.writer.Writer;

import java.util.List;

@Configuration
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public JobExecListener jobExecutionListener;

	@Bean
	public Job job(Step step) {
		Job job = jobBuilderFactory.get("job1")
				.listener(jobExecutionListener)
				.flow(step)
				.end()
				.build();
		return job;
	}

	@Bean
	public Step step() {

		TaskletStep step = stepBuilderFactory.get("step1")
				.<List<ProductDTO>,Product>chunk(100)
				.reader(new Reader())
				.processor(new Processor())
				.writer(new Writer())
				.listener(readerListener())
				.listener(processListener())
				.build();
		return step;
	}

	@Bean
	public ReaderListener readerListener() {
		return new ReaderListener();
	}

	@Bean
	public ProcessListener processListener() {
		return new ProcessListener();
	}

	@Bean
	public JobExecListener listener() {
		return JobExecListener();
	}



}
