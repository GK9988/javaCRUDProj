package com.garvk.CrudProj.controllers;

import com.garvk.CrudProj.models.User;
import com.garvk.CrudProj.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("register")
    public ResponseEntity<?> addUser(@RequestBody User user){
        User aOutUser = userService.addUser(user);
        return new ResponseEntity<>(aOutUser, HttpStatus.CREATED);
    }
}
