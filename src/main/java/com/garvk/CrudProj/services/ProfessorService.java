package com.garvk.CrudProj.services;

import com.garvk.CrudProj.models.Professor;
import com.garvk.CrudProj.repositories.ProfessorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepo professorRepo;


    public void addProfessor(Professor aInProfessor) {
        professorRepo.save(aInProfessor);
    }
}
