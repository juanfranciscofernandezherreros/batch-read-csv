package com.fernandez.tasklet;

import com.fernandez.dto.EmailBody;
import com.fernandez.service.DownloadFileService;
import com.fernandez.service.EmailPort;
import com.fernandez.service.impl.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendEmailTasklet implements Tasklet {

    @Autowired
    private EmailPort emailPort;

    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailTasklet.class);

    public SendEmailTasklet(EmailPort emailPort) {
        this.emailPort = emailPort;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext chunkContext) {
        EmailBody emailBody = new EmailBody();
        emailBody.setEmail("jnfz92@gmail.com");
        emailBody.setContent("content");
        emailBody.setSubject("subject");
        emailPort.sendEmail(emailBody);
        return RepeatStatus.FINISHED;
    }

}
