package com.fernandez.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WriterListener implements ItemWriteListener<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriterListener.class);

    @Override
    public void beforeWrite(List<? extends String> list) {
        LOGGER.info("beforeWrite");

    }


    @Override
    public void afterWrite(List<? extends String> list) {
        for (String creditCardRisk : list) {
            LOGGER.info("afterWrite :" + creditCardRisk);
        }
    }

    @Override
    public void onWriteError(Exception e, List<? extends String> list) {
        LOGGER.info("onWriteError");
    }
}
