package com.garvk.CrudProj.controllers;

import com.garvk.CrudProj.models.Professor;
import com.garvk.CrudProj.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Professor")
@CrossOrigin
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping("/")
    public ResponseEntity<?> addProfessor(@RequestBody Professor aInProfessor){
        try{
            professorService.addProfessor(aInProfessor);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(aInProfessor, HttpStatus.CREATED);
    }

}
