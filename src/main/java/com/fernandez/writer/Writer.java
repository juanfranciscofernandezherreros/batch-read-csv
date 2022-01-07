package com.fernandez.writer;

import com.fernandez.entity.Product;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class Writer implements ItemWriter<Product> {

    @Override
    public void write(List<? extends Product> messages) throws Exception {
        for (Product msg : messages) {
            System.out.println("Writing the data " + msg);
        }
    }
}
