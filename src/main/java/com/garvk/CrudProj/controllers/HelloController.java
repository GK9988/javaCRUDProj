package com.garvk.CrudProj.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("api")
    public String helloWorld(){
        return "Hello Bachoo!";
    }
}
