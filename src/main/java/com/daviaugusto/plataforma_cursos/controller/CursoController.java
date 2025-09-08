package com.daviaugusto.plataforma_cursos.controller;


import com.daviaugusto.plataforma_cursos.infrastructure.dtos.request.CursoRequest;
import com.daviaugusto.plataforma_cursos.infrastructure.dtos.response.CursoResponse;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Curso;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.entidadesMux.Data;
import com.daviaugusto.plataforma_cursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {


    @Autowired
    private CursoService cursoService;


    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping
    public ResponseEntity<CursoResponse> salvarCurso(@RequestBody CursoRequest curso,
                                                     @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(cursoService.salvarCurso(curso, token));
    }

    @PreAuthorize("hasRole('ALUNO')")
    @PostMapping("/{id}")
    public ResponseEntity<Void> inscreveAluno(@RequestHeader("Authorization") String token,
                                              @PathVariable Long id){
        cursoService.inscreverAluno(id, token);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping("/{idCurso}/{idVideo}")
    public ResponseEntity<Void> adicionarVideo(@PathVariable Long idCurso,
                                               @PathVariable Long idVideo,
                                               @RequestHeader("Authorization") String token){
        cursoService.adicionarVideo(idCurso, idVideo, token);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/aulas")
    public ResponseEntity<List<Data>> buscarAulas(@PathVariable Long id){
        return ResponseEntity.ok(cursoService.buscarVideos(id));
    }

    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> buscarCursoPorId(@PathVariable Long id){
        return ResponseEntity.ok(cursoService.buscarCursoPorId(id));

    }

    @PreAuthorize("hasRole('ALUNO')")
    @GetMapping
    public ResponseEntity<List<CursoResponse>> buscarCursosInscritos(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(cursoService.buscarCursosIncritos(token));
    }


}
