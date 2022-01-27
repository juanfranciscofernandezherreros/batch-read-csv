package com.fernandez.listener;

import com.fernandez.dto.ProductDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class MyCustomReader extends FlatFileItemReader<ProductDTO> implements ItemReader<ProductDTO>{

    public MyCustomReader() {
        setResource(new FileSystemResource("input/ficheroCarga_RiskGroups.csv"));
        setLinesToSkip(1);
        setLineMapper(getDefaultLineMapper());
    }

    public DefaultLineMapper<ProductDTO> getDefaultLineMapper() {
        DefaultLineMapper<ProductDTO> defaultLineMapper = new DefaultLineMapper<ProductDTO>();
        DelimitedLineTokenizer delimitedLineTokenizer =new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(new String[] { "RiskGroup", "Descripction"});
        delimitedLineTokenizer.setDelimiter(";");
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        BeanWrapperFieldSetMapper<ProductDTO> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<ProductDTO>();
        beanWrapperFieldSetMapper.setTargetType(ProductDTO.class);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
        return defaultLineMapper;
    }
}
