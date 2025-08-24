package com.daviaugusto.plataforma_cursos.infrastructure.repositories;

import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Aluno;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
