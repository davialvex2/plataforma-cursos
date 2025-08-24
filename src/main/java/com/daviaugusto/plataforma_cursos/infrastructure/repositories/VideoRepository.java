package com.daviaugusto.plataforma_cursos.infrastructure.repositories;

import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Curso;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Data, Long> {

}
