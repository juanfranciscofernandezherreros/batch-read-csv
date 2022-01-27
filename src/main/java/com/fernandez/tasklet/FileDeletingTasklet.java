package com.fernandez.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class FileDeletingTasklet implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDeletingTasklet.class);

    private Resource directory;

    public FileDeletingTasklet() {
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext chunkContext) {
        return RepeatStatus.FINISHED;
    }

}
