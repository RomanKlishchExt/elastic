package com.rk.elastic.service;

import com.rk.elastic.model.Article;
import com.rk.elastic.model.Type;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LoadService {

    public List<Article> load(MultipartFile inputStream) {
        List<Article> articleList = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream.getInputStream()));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {

                articleList.add(Article.builder()
                                .id(Long.valueOf(csvRecord.get(0)))
                                .title(csvRecord.get(1))
                                .body(csvRecord.get(2))
                                .tags(csvRecord.get(3))
                                .createDate(csvRecord.get(4))
                                .type(Type.valueOf(csvRecord.get(5)))
                        .build());
            }
            return articleList;
        } catch (Exception exception) {
            log.error("Unable to open file", exception);
            throw new IllegalArgumentException("Unable to open file", exception);
        }
    }
}
