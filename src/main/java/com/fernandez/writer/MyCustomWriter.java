package com.fernandez.writer;

import com.fernandez.dto.Product;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyCustomWriter implements ItemWriter<Product> {

    @Override
    public void write(List<? extends Product> messages) throws Exception {
        for (Product msg : messages) {
            System.out.println("Writing the data " + msg);
        }
    }
}
