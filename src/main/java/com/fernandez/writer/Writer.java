package com.fernandez.writer;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class Writer implements ItemWriter<String> {

    @Override
    public void write(List items) throws Exception {
        items.stream().forEach(System.out::println);
        System.out.println(" ************ writing each chunck ***********");
    }

}
