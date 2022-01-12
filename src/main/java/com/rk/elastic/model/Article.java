package com.rk.elastic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;

import static com.rk.elastic.common.Constant.DATE_TIME_STANDARD;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "article")
public class Article {
    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String title;
    @Field(type = FieldType.Text)
    private String body;
    @Field(type = FieldType.Keyword)
    private List<String> tags;
    @Field(type = FieldType.Keyword)
    private Type type;
    @Field(type = FieldType.Date,  pattern = "uuuu-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
}