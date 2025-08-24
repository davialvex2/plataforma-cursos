package com.daviaugusto.plataforma_cursos.controller;


import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Curso;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Data;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Usuario;
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
    public ResponseEntity<Curso> salvarCurso(@RequestBody Curso curso,
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

    /*@GetMapping("/{id}")
    public ResponseEntity<List<Data>> buscarAulas(@PathVariable Long id){
        return ResponseEntity.ok(cursoService.buscarVideos(id));
    }*/

    @PreAuthorize("hasRole('PROFESSOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable Long id,
                                                  @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(cursoService.buscarCursoPorId(id));

    }

    @GetMapping
    public ResponseEntity<List<Curso>> buscarCursosInscritos(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(cursoService.buscarCursosIncritos(token));
    }


}
