package com.fernandez.listeners;

import org.springframework.batch.core.JobExecution;

public interface JobExecListener {
    void beforeJob(JobExecution var1);

    void afterJob(JobExecution var1);
}