package com.example.elasticsearch.service;


import com.example.elasticsearch.model.User;
import com.example.elasticsearch.repository.LogsRepository;
import com.example.elasticsearch.repository.UserRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserService {
    private final Logger logger= LoggerFactory.getLogger(UserService.class);
    UserRepository userRepository;
    LogsRepository logsRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public void save(User user) {
        userRepository.save(user);
    }

    public UserService(UserRepository userRepository, ElasticsearchOperations elasticsearchOperations,LogsRepository logsRepository) {
        this.userRepository = userRepository;
        this.logsRepository=logsRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public List<User> findAll() {
        return userRepository.findAlByDeleted(false);
    }
    public Optional<User> findByName(String name) {
        return userRepository.findByNameaAndAndDeleted(name,false);
    }

    public ResponseEntity<String> delete(long id){
           Optional<User> user =userRepository.findById(id);
            User user1=user.get();
            user1.setDeleted(true);
             logger.info("You are here");
             logger.info("You are Trying to delete user : "+id);
            userRepository.save(user1);
        return new ResponseEntity<>("",HttpStatus.NO_CONTENT);
      }


    public List<String> fetchSuggestions(String query) {
        query=query.toLowerCase(Locale.ROOT);

        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("name", query+"*");
        System.out.println("QueryBul "+queryBuilder);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(PageRequest.of(0, 5))
                .build();
        System.out.println("sear "+searchQuery);

        SearchHits<User> searchSuggestions =
                elasticsearchOperations.search(searchQuery,
                        User.class,
                        IndexCoordinates.of("user"));
        System.out.println("searSuq "+searchSuggestions);

        List<String> suggestions = new ArrayList<>();

        searchSuggestions.getSearchHits().forEach(searchHit-> suggestions.add(searchHit.getContent().getName()));
        System.out.println(suggestions);
        return suggestions;
    }
}
