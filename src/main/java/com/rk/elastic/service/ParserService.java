package com.rk.elastic.service;

import com.rk.elastic.model.Article;
import com.rk.elastic.model.Type;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.rk.elastic.common.Constant.DATE_TIME_STANDARD;

@Slf4j
@Service
public class ParserService {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_STANDARD);
    private String TRIM_REGEX = "<code>(.|\\n)*?<\\/code>";

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
                        .tags(splitTagField(csvRecord.get(3)))
                         .createDate(LocalDateTime.parse(csvRecord.get(4), formatter))
                        .type(Type.valueOf(csvRecord.get(5)))
                        .build());
            }
            return articleList;
        } catch (Exception exception) {
            log.error("Unable to open file", exception);
            throw new IllegalArgumentException("Unable to open file", exception);
        }
    }

    private String cutUseless(String body) {
        return body.replaceAll(TRIM_REGEX, "");
    }

    private List<String> splitTagField(String tags) {
        return Arrays.stream(tags.split("\\W+"))
                .filter(StringUtils::hasText)
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
