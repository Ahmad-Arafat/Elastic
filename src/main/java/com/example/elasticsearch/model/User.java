package com.example.elasticsearch.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Component
@Document(indexName = "user")
public class User {
    @Id
    public Long id;

    @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    public String name;

    public String teamName;
    public boolean deleted;
    public long salary;
}
