package com.daviaugusto.plataforma_cursos.infrastructure.repositories;

import com.daviaugusto.plataforma_cursos.infrastructure.entitys.PlaybackId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaybackIdRepository extends JpaRepository<PlaybackId, Long> {
}
