package com.fernandez.springbatch.writer;

import com.fernandez.springbatch.model.CreditCardRisk;
import com.fernandez.springbatch.repository.CreditCardRiskRespository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

public class CreditCardItemWriter implements ItemWriter<CreditCardRisk> {

    @Autowired
    private CreditCardRiskRespository respository;

    @Override
    public void write(List<? extends CreditCardRisk> list) throws Exception {
        LOGGER.info("CreditCard" + list);
        respository.saveAll(list);
    }
}
