package com.fernandez.listener;

import com.fernandez.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class WriterListener implements ItemWriteListener<Product> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriterListener.class);

    @Override
    public void beforeWrite(List<? extends Product> list) {
        LOGGER.info("beforeWrite");
    }


    @Override
    public void afterWrite(List<? extends Product> list) {
        for (Product creditCardRisk : list) {
            LOGGER.info("afterWrite :" + creditCardRisk);
        }
    }

    @Override
    public void onWriteError(Exception e, List<? extends Product> list) {
        LOGGER.info("onWriteError");
    }
}
