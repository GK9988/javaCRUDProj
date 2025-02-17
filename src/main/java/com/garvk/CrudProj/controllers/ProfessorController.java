package com.garvk.CrudProj.controllers;

import com.garvk.CrudProj.models.Professor;
import com.garvk.CrudProj.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public ResponseEntity<?> getAllProfessor(){
        List<Professor> lProfessorList = professorService.getAllProfessors();

        if(lProfessorList.isEmpty()){
            return new ResponseEntity<>("No Records Found", HttpStatus.OK);
        }

        return new ResponseEntity<>(lProfessorList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfessorById(@PathVariable Integer id){
        Professor lProfessor = professorService.getProfessorById(id);

        if(null == lProfessor){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(lProfessor, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateProfessor(@RequestBody Professor aInProfessor){
        try{
            professorService.addProfessor(aInProfessor);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(aInProfessor, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfessor(@PathVariable Integer id){
        try{
            int result = professorService.deleteProfessor(id);
            if(result == -1){
                return new ResponseEntity<>("Key Not Found", HttpStatus.NOT_FOUND);
            }
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }



}
