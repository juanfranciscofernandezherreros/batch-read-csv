package com.fernandez.listener;

import com.fernandez.dto.Product;
import com.fernandez.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;

public class ProcessListener implements ItemProcessListener<ProductDTO, Product> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessListener.class);

    @Override
    public void beforeProcess(ProductDTO creditCard) {
        LOGGER.info("beforeProcess");
    }

    @Override
    public void afterProcess(ProductDTO creditCard, Product creditCardRisk) {
        LOGGER.info("afterProcess: " + creditCard + " ---> " + creditCardRisk);
    }

    @Override
    public void onProcessError(ProductDTO creditCard, Exception e) {
        LOGGER.info("onProcessError");
    }
}
