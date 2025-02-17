package com.garvk.CrudProj.services;

import com.garvk.CrudProj.models.Professor;
import com.garvk.CrudProj.models.Student;
import com.garvk.CrudProj.repositories.ProfessorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepo professorRepo;

    @Autowired
    StudentService studentService;


    public void addProfessor(Professor aInProfessor) {
        professorRepo.save(aInProfessor);
    }

    public List<Professor> getAllProfessors() {
        return professorRepo.findAll();
    }

    public Professor getProfessorById(Integer id) {
        return professorRepo.findById(id).orElse(null);
    }

    public int deleteProfessor(Integer id) {
        Professor lProfessor = professorRepo.findById(id).orElse(null);
        if(null == lProfessor) return -1;
        professorRepo.delete(lProfessor);
        return 1;
    }

    public void updateMarks(int aInUid, int aInMarks, Integer aInProfId) {

        Professor lProfessor = professorRepo.findById(aInProfId).orElse(null);

        if(null == lProfessor){
            throw new IllegalArgumentException("Professor with Id: " + aInProfId + " Not Found");
        }


        Student lStudent = studentService.getStudentById(aInUid);

        if(null == lStudent){
            throw new IllegalArgumentException("Student with Id: " + aInUid + " Not Found");
        }

        String lCourseName = lProfessor.getCourse();

        //check whether the student has that course or not
        if(!lStudent.getCourses().contains(lCourseName)){
            throw new IllegalArgumentException("Given Student is not enrolled in your subject.");
        }

        //Update the marks
        lStudent.getMarksSet().put(lCourseName, aInMarks);

        studentService.addStudent(lStudent);


    }
}
