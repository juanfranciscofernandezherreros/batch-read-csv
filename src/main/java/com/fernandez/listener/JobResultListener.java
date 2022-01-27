package com.fernandez.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobResultListener implements JobExecutionListener {

    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Called beforeJob().");
    }

    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED ) {
            System.out.println("completed");
        }
        else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            System.out.println("failed");
        }
    }
}
