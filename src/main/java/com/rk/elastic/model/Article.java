package com.rk.elastic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "article")
public class Article {
    @Id
    private Long id;
    private String title;
    private String body;
    private String tags;
    private Type type;
    @Field(type= FieldType.Date)
    private String createDate;
}
