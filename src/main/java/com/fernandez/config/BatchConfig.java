package com.fernandez.config;

import com.fernandez.dto.Product;
import com.fernandez.dto.ProductDTO;
import com.fernandez.listener.*;
import com.fernandez.processor.MyCustomProcessor;
import com.fernandez.reader.MyCustomReader;
import com.fernandez.tasklet.FileDeletingTasklet;
import com.fernandez.tasklet.FileDownloadTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fernandez.writer.MyCustomWriter;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

@Configuration
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Value("file:./input/*.csv")
	private Resource[] inputResources;

	@Bean
	public Job demoJob(){
		return jobBuilderFactory.get("demoJob")
				.incrementer(new RunIdIncrementer())
				.start(stepDownloadTasklet())
				//.next(stepSaveCsv())
				.next(stepDeleteTasklet())
				.build();
	}

	@Bean
	public Step stepDownloadTasklet(){
		return stepBuilderFactory.get("stepOne")
				.tasklet(new FileDownloadTasklet())
				.build();
	}

	@Bean
	public Step stepSaveCsv() {
		return stepBuilderFactory.get("stepTwo")
				.<ProductDTO,Product>chunk(1)
				.reader(new MyCustomReader())
				.processor(new MyCustomProcessor())
				.writer(new MyCustomWriter())
				.build();
	}

	@Bean
	public Step stepDeleteTasklet(){
		FileDeletingTasklet task = new FileDeletingTasklet();
		task.setResources(inputResources);
		return stepBuilderFactory.get("step3")
				.tasklet(task)
				.build();
	}



}
