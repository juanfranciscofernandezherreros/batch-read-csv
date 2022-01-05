package com.fernandez.springbatch.listeners;

import com.fernandez.springbatch.model.CreditCard;
import com.fernandez.springbatch.model.CreditCardRisk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;

public class CreditCardItemProcessListener implements ItemProcessListener<CreditCard, CreditCardRisk> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardItemProcessListener.class);

    @Override
    public void beforeProcess(CreditCard creditCard) {
        LOGGER.info("beforeProcess");
    }

    @Override
    public void afterProcess(CreditCard creditCard, CreditCardRisk creditCardRisk) {
        LOGGER.info("afterProcess: " + creditCard + " ---> " + creditCardRisk);
    }

    @Override
    public void onProcessError(CreditCard creditCard, Exception e) {
        LOGGER.info("onProcessError");
    }
}