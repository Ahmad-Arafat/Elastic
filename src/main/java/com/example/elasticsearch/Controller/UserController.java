package com.example.elasticsearch.Controller;

import com.example.elasticsearch.exception.ApiRequestException;
import com.example.elasticsearch.model.Logs;
import com.example.elasticsearch.model.User;
import com.example.elasticsearch.service.LogService;
import com.example.elasticsearch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    public UserController(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }
    final
     UserService userService;
     LogService logService;


    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<Optional<User>> findByName(@PathVariable String name){
        return  ResponseEntity.ok(userService.findByName(name));
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/activeDelete/{id}")
    public ResponseEntity<String> activeDelete(@PathVariable String id){
        try{
        return logService.activeDelete(id);
    }
         catch (Exception exc) {
        throw new ApiRequestException(
                "Failed to activate");
    }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> DeleteUser(@PathVariable long id){
      try{
        return  userService.delete(id);
    }
         catch (Exception e) {
        throw new ApiRequestException(
                "Id not found");
    }
    }

    @GetMapping("/suggestions/{letter}")
    public List<String> fetchSuggestions(@PathVariable String letter) {
        return userService.fetchSuggestions(letter);
    }

    @GetMapping("/getLogs")
    public List<Logs> getAllLogs(){
        return logService.getAllLogs();
    }

}