package com.fernandez.tasklet;

import com.fernandez.service.DownloadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileDownloadTasklet implements Tasklet {

    @Autowired
    private DownloadFileService downloadFileService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloadTasklet.class);

    public FileDownloadTasklet(DownloadFileService downloadFileService) {
        this.downloadFileService = downloadFileService;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext chunkContext) {
        downloadFileService.downloadFile("./risk_groups.csv","./risk_groups");
        return RepeatStatus.FINISHED;
    }

}
