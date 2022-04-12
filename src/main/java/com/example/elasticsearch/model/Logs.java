package com.example.elasticsearch.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Component
@Document(indexName = "filebeat-7.17.1-2022.03.28")
public class Logs {
    @Id
    public String id;
//    @NonNull
    public String message;
    public String userId;
   public boolean activated;

}
