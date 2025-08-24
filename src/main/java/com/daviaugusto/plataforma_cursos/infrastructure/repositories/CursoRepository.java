package com.daviaugusto.plataforma_cursos.infrastructure.repositories;

import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByNome(String nome);

}
