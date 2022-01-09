package com.fernandez.processor;
import com.fernandez.dto.ProductDTO;
import com.fernandez.entity.Product;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;

public class Processor implements ItemProcessor<List<ProductDTO>, Product> {

    @Override
    public Product process(List<ProductDTO> resources) {
        return new Product();
    }

}
