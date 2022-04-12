package com.example.elasticsearch.repository;


import com.example.elasticsearch.model.User;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends ElasticsearchRepository<User,Long> {
    List<User> findAll();
    Optional<User> findByNameaAndAndDeleted(String name,boolean b);




    List<User> findAlByDeleted(boolean b);
}


