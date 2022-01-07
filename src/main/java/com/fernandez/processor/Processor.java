package com.fernandez.processor;
import com.fernandez.dto.ProductDTO;
import com.fernandez.entity.Product;
import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<ProductDTO, Product> {

    @Override
    public Product process(ProductDTO data) {
        return new Product(data.getProductId(),data.getProdName(),data.getPrice(),data.getUnit(),data.getProductDesc());
    }

}
