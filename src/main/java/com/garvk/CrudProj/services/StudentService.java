package com.garvk.CrudProj.services;

import com.garvk.CrudProj.models.Student;
import com.garvk.CrudProj.repositories.ResultDto;
import com.garvk.CrudProj.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    private void setMarksSet(Student aInStudent){

        HashMap<String, Integer> courseMap = new HashMap<>();
        List<String> courseList = aInStudent.getCourses();
        HashMap<String, Integer> lStudentCourseMap = aInStudent.getMarksSet();

        for(String course: courseList){
            if(null == lStudentCourseMap || null == lStudentCourseMap.get(course)){
                courseMap.put(course, 0);
            } else {
                courseMap.put(course, lStudentCourseMap.get(course));
            }
        }

        aInStudent.setMarksSet(courseMap);
    }


    public Student addStudent(Student aInStudent) {

        setMarksSet(aInStudent);

        return studentRepo.save(aInStudent);
    }

    public int deleteStudent(Integer id) {
        Student lStudent = studentRepo.findById(id).orElse(null);

        if(null == lStudent) return -1;

        studentRepo.delete(lStudent);

        return 0;
    }

    public Student getStudentById(Integer id) {
        return studentRepo.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public ResultDto getResult(int aInId) {
        Student lStudent = studentRepo.findById(aInId).orElse(null);

        if(null == lStudent){
            throw new IllegalArgumentException("No Student with Id: " + aInId + " Found");
        }

        return generateResult(lStudent);


    }

    private ResultDto generateResult(Student aInStudent){
        ResultDto lResultDto = new ResultDto();

        lResultDto.setName(aInStudent.getName());
        lResultDto.setUid(aInStudent.getUid());
        lResultDto.setCourseMap(aInStudent.getMarksSet());

        calculateResult(lResultDto);

        return lResultDto;
    }

    private void calculateResult(ResultDto aInResultDto){
        int totalMarks = 0;

        for(Integer marks: aInResultDto.getCourseMap().values()){
            totalMarks += marks;
        }

        aInResultDto.setCgpa((float) (totalMarks) / (aInResultDto.getCourseMap().size() * 10));
    }
}
