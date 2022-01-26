package com.fernandez.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;

public class ProcessListener implements ItemProcessListener<String, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessListener.class);

    @Override
    public void beforeProcess(String creditCard) {
        LOGGER.info("beforeProcess");
    }

    @Override
    public void afterProcess(String creditCard, String creditCardRisk) {
        LOGGER.info("afterProcess: " + creditCard + " ---> " + creditCardRisk);
    }

    @Override
    public void onProcessError(String creditCard, Exception e) {
        LOGGER.info("onProcessError");
    }
}
