package com.example.L13.L13.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.L13.L13.models.UserInfo;
import com.example.L13.L13.requests.CreateUserRequestDTO;
import com.example.L13.L13.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> createAUser(@Valid @RequestBody CreateUserRequestDTO userInfo){
        log.info("Request Received {} " , userInfo);
        return new ResponseEntity<>(userService.createUser(userInfo), HttpStatus.CREATED);
    }


    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserInfo>> fetchAllUsers(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber){
        log.debug("Request Received for fetching all users  ");
        return new ResponseEntity<>(userService.fetchAllUsers(pageNumber), HttpStatus.OK);
    }


    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<UserInfo>> fetchUserById(
            @RequestParam( name = "userId", defaultValue = "0") Long id){
        log.debug("Request Received for fetching user by Id {}",id);
        return new ResponseEntity<>(userService.fetchUserById(id), HttpStatus.OK);
    }
}
