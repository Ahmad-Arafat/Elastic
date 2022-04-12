package com.example.elasticsearch.repository;


import com.example.elasticsearch.model.Logs;
import com.example.elasticsearch.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogsRepository extends ElasticsearchRepository<Logs,String> {
    List<Logs> findAllByActivated(boolean b);

}


