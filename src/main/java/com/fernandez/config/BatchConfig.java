package com.fernandez.config;

import com.fernandez.dto.Product;
import com.fernandez.dto.ProductDTO;
import com.fernandez.processor.MyCustomProcessor;
import com.fernandez.reader.MyCustomReader;
import com.fernandez.service.DownloadFileService;
import com.fernandez.service.DownloadTransferServiceImpl;
import com.fernandez.service.EmailPort;
import com.fernandez.tasklet.FileDeletingTasklet;
import com.fernandez.tasklet.FileDownloadTasklet;
import com.fernandez.tasklet.SendEmailTasklet;
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
import org.springframework.core.io.Resource;

import javax.validation.constraints.Email;

@Configuration
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Value("file:./input/*.csv")
	private Resource[] inputResources;

	@Autowired
	private DownloadFileService downloadFileService;

	@Autowired
	private EmailPort emailPort;

	@Bean
	public Job demoJob(){
		return jobBuilderFactory.get("demoJob")
				.incrementer(new RunIdIncrementer())
				//.start(stepDownloadTasklet())
				.start(stepSaveCsv())
				//.next(stepDeleteTasklet())
				//.start(stepEmailPort())
				.build();
	}

	@Bean
	public Step stepDownloadTasklet(){
		FileDownloadTasklet fileDownloadTasklet = new FileDownloadTasklet(downloadFileService);
		return stepBuilderFactory.get("stepDownloadTasklet")
				.tasklet(fileDownloadTasklet)
				.build();
	}

	@Bean
	public Step stepSaveCsv() {
		return stepBuilderFactory.get("stepSaveCsv")
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
		return stepBuilderFactory.get("stepDeleteFileCsv")
				.tasklet(task)
				.build();
	}

	@Bean
	public Step stepEmailPort(){
		SendEmailTasklet task = new SendEmailTasklet(emailPort);
		return stepBuilderFactory.get("stepEmail")
				.tasklet(task)
				.build();
	}


}
