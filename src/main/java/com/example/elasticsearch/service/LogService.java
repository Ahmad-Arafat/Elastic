package com.example.elasticsearch.service;


import com.example.elasticsearch.model.Logs;
import com.example.elasticsearch.repository.LogsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LogService {
   
   
    LogsRepository logsRepository;
    public LogService(LogsRepository logsRepository) {
        this.logsRepository=logsRepository;
    }

    public List<Logs> getAllLogs() {
        return  logsRepository.findAllByActivated(false) ;
    }

    public ResponseEntity<String> activeDelete(String id) {
        Optional<Logs> l = logsRepository.findById(id);
        Logs l1 = l.get();
        l1.setActivated(true);
        logsRepository.save(l1);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
