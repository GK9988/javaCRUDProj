package com.garvk.CrudProj.controllers;

import com.garvk.CrudProj.models.Student;
import com.garvk.CrudProj.repositories.ResultDto;
import com.garvk.CrudProj.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ResourceBundle;


@RestController
@RequestMapping("/Student")
@CrossOrigin
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/")
    public ResponseEntity<?> addStudent(@RequestBody Student aInStudent){
        Student student = null;
        try{
            student = studentService.addStudent(aInStudent);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateStudent(@RequestBody Student aInStudent){
        Student student = null;
        try{
            student = studentService.addStudent(aInStudent);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id){
        try{
            int result = studentService.deleteStudent(id);
            if(result == -1){
                return new ResponseEntity<>("Key not Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Integer id){
        Student aOutStudent = studentService.getStudentById(id);

        if(null == aOutStudent){
            return new ResponseEntity<>("Student Not Found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(aOutStudent, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllStudents(){
        List<Student> aOutStudentList = studentService.getAllStudents();

        if(aOutStudentList.isEmpty()){
            return new ResponseEntity<>("No Records Found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(aOutStudentList, HttpStatus.OK);
    }

    @GetMapping("/{id}/Result")
    public ResponseEntity<?> getResult(@PathVariable Integer id){
        ResultDto aOutResultDto = null;

        try{
            aOutResultDto = studentService.getResult(id);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(aOutResultDto, HttpStatus.OK);
    }

}
