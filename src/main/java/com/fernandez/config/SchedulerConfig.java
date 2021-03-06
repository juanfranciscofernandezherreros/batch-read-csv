package com.fernandez.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;

@Configuration
public class SchedulerConfig {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;



    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    @Scheduled(cron="*/60 * * * * ?")
    public void scheduleByFixedRate() throws Exception {
        System.out.println("Batch job starting");
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(job, jobParameters);
        System.out.println("Batch job executed successfully\n");
    }

}
