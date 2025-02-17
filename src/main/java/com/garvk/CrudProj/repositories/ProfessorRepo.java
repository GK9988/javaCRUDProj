package com.garvk.CrudProj.repositories;

import com.garvk.CrudProj.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepo extends JpaRepository<Professor, Integer> {
}
