package com.fernandez.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
public class MyCustomReaderListener implements ItemReadListener<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyCustomReaderListener.class);

    @Override
    public void beforeRead() {
        LOGGER.info("beforeRead");
    }

    @Override
    public void afterRead(String creditCard) {
        LOGGER.info("afterRead: " + creditCard.toString());
    }

    @Override
    public void onReadError(Exception e) {
        LOGGER.info("onReadError");
    }
}
