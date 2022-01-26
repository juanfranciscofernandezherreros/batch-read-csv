package com.fernandez.processor;
import com.fernandez.dto.Product;
import com.fernandez.dto.ProductDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyCustomProcessor implements ItemProcessor<ProductDTO, Product> {

    @Override
    public Product process(ProductDTO data) {
        return new Product(data.getDescription(),data.getRiskGroup());
    }

}
