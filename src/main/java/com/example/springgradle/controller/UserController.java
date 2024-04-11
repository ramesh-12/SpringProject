package com.example.springgradle.controller;

import com.example.springgradle.repository.EmployeeEntity;
import com.example.springgradle.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<String> getUserName(@PathVariable Long id){
        String name = userService.getUsernameById(id);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody EmployeeEntity employee){
        userService.saveUser(employee);
        return new ResponseEntity<>("Saved", HttpStatus.OK);
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PutMapping("/Update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody EmployeeEntity employee){
        userService.updateUser(id,employee);
        return new ResponseEntity<>("Update", HttpStatus.OK);
    }
}
