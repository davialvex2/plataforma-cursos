package com.daviaugusto.plataforma_cursos.infrastructure.mapper;


import com.daviaugusto.plataforma_cursos.infrastructure.dtos.request.CursoRequest;
import com.daviaugusto.plataforma_cursos.infrastructure.dtos.response.AlunoResponse;
import com.daviaugusto.plataforma_cursos.infrastructure.dtos.response.CursoResponse;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Aluno;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CursoConverter {

    @Autowired
    UsuarioConverter usuarioConverter;


    public Curso paraCurso(CursoRequest cursoRequest){
        return Curso.builder().nome(cursoRequest.getNome())
                .descricao(cursoRequest.getDescricao()).build();
    }

    public CursoResponse paraCursoResponse(Curso curso){
        return CursoResponse.builder().id(curso.getId())
                .nome(curso.getNome())
                .descricao(curso.getDescricao())
                .professorResponse(curso.getProfessor() != null ? usuarioConverter.paraProfessorResponse(curso.getProfessor()) : null)
                .alunoResponse(curso.getAlunos() != null ? paraListaAlunoResponse(curso.getAlunos()) : null)
                .build();

    }

    public List<AlunoResponse> paraListaAlunoResponse(List<Aluno> alunos){
        return alunos.stream().map(aluno -> usuarioConverter.paraAlunoResponse(aluno)).toList();
    }


    public List<CursoResponse> paraListaCursoResponse(List<Curso> cursos){
        return cursos.stream().map(this::paraCursoResponse).toList();
    }


}
