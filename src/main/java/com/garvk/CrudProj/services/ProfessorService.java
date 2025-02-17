package com.garvk.CrudProj.services;

import com.garvk.CrudProj.models.Professor;
import com.garvk.CrudProj.repositories.ProfessorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepo professorRepo;


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
}
