package com.fernandez.springbatch.job;

import com.fernandez.springbatch.model.CreditCard;
import com.fernandez.springbatch.model.CreditCardRisk;
import com.fernandez.springbatch.listeners.CreditCardIItemReaderListener;
import com.fernandez.springbatch.listeners.CreditCardIItemWriterListener;
import com.fernandez.springbatch.listeners.CreditCardItemProcessListener;
import com.fernandez.springbatch.listeners.CreditCardJobExecutionListener;
import com.fernandez.springbatch.processor.CreditCardItemProcessor;
import com.fernandez.springbatch.reader.CreditCardItemReader;
import com.fernandez.springbatch.writer.CreditCardItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public CreditCardItemReader reader() {
        return new CreditCardItemReader();
    }

    @Bean
    public CreditCardItemProcessor processor() {
        return new CreditCardItemProcessor();
    }

    @Bean
    public CreditCardItemWriter writer() {
        return new CreditCardItemWriter();
    }

    @Bean
    public CreditCardJobExecutionListener jobExecutionListener() {
        return new CreditCardJobExecutionListener();
    }

    @Bean
    public CreditCardIItemReaderListener readerListener() {
        return new CreditCardIItemReaderListener();
    }

    @Bean
    public CreditCardItemProcessListener creditCardItemProcessListener() {
        return new CreditCardItemProcessListener();
    }

    @Bean
    public CreditCardIItemWriterListener writerListener() {
        return new CreditCardIItemWriterListener();
    }

    @Bean
    public Job job(Step step, CreditCardJobExecutionListener jobExecutionListener) {
        Job job = jobBuilderFactory.get("job1")
                .listener(jobExecutionListener)
                .flow(step)
                .end()
                .build();
        return job;
    }

    @Bean
    public Step step(CreditCardItemReader reader,
                     CreditCardItemWriter writer,
                     CreditCardItemProcessor processor,
                     CreditCardIItemReaderListener readerListener,
                     CreditCardItemProcessListener creditCardItemProcessListener,
                     CreditCardIItemWriterListener writerListener) {

        TaskletStep step = stepBuilderFactory.get("step1")
                .<CreditCard, CreditCardRisk>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(readerListener)
                .listener(creditCardItemProcessListener)
                .listener(writerListener)
                .build();
        return step;
    }

}
