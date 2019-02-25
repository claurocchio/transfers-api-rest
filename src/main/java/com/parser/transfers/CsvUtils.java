package com.parser.transfers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class CsvUtils {

    @Value("${input.path}")
    private String inputPath;

    public <T> List<T> loadObjectList(Class<T> clazz, String fileName) {
        try {
            CsvSchema schema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            String filePath = inputPath + fileName;
            File file = new ClassPathResource(filePath).getFile();
            mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
            ObjectReader reader = mapper.readerFor(clazz).with(schema);
            return reader.<T>readValues(file).readAll();
        } catch (Exception e) {
            log.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }
}
