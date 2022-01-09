package com.fernandez.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernandez.dto.ProductDTO;
import org.springframework.batch.item.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader implements ItemReader<List<ProductDTO>> {

    private String filePattern;

    @Override
    public List<ProductDTO> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("READER");
        filePattern = "file:input/product*.json";
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = patternResolver.getResources(filePattern);
        List<Resource> newList = Arrays.stream(resources).collect(Collectors.toList());
        List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
        for (int i=0;i<newList.size();i++) {
            productDTOList.add(productDTO(newList.get(i)));
        }
        return productDTOList;
    }

    private ProductDTO productDTO(Resource resource) {
        ProductDTO productDTOList = new ProductDTO();
        ObjectMapper mapper = new ObjectMapper();
        ProductDTO productDTO = null;
        try {
            productDTO = mapper.readValue(Paths.get(resource.getFile().getAbsolutePath()).toFile(), ProductDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productDTO;
    }

    private String mapTo(Resource x) {
        return x.getFilename();
    }
}
