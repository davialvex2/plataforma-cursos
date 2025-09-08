package com.daviaugusto.plataforma_cursos.infrastructure.mapper;


import com.daviaugusto.plataforma_cursos.infrastructure.dtos.request.AlunoRequest;
import com.daviaugusto.plataforma_cursos.infrastructure.dtos.request.ProfessorRequest;
import com.daviaugusto.plataforma_cursos.infrastructure.dtos.request.UsuarioRequest;
import com.daviaugusto.plataforma_cursos.infrastructure.dtos.response.AlunoResponse;
import com.daviaugusto.plataforma_cursos.infrastructure.dtos.response.ProfessorResponse;
import com.daviaugusto.plataforma_cursos.infrastructure.dtos.response.UsuarioResponse;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Aluno;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Professor;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;


@Component
public class UsuarioConverter {





      public Usuario paraUsuario(UsuarioRequest usuarioRequest){
          return Usuario.builder().email(usuarioRequest.getEmail())
                  .senha(usuarioRequest.getSenha())
                  .role(usuarioRequest.getRole())
                  .aluno(usuarioRequest.getAluno() != null ? paraAluno(usuarioRequest.getAluno()) : null)
                  .professor(usuarioRequest.getProfessor() != null ? paraProfessor(usuarioRequest.getProfessor()) : null)
                  .build();
      }


      public Aluno paraAluno(AlunoRequest alunoRequest){
          return Aluno.builder().nome(alunoRequest.getNome())
                  .matricula(alunoRequest.getMatricula())
                  .dataNascimento(alunoRequest.getDataNascimento())
                  .cursos(null).build();
      }

      public Professor paraProfessor(ProfessorRequest professorRequest){
          return Professor.builder().nome(professorRequest.getNome())
                  .especialidade(professorRequest.getEspecialidade())
                  .dataNascimento(professorRequest.getDataNascimento())
                  .cursosCriado(null).build();
      }


      public UsuarioResponse paraUsuarioResponse(Usuario usuario){
          return UsuarioResponse.builder().id(usuario.getId())
                  .email(usuario.getEmail())
                  .senha(usuario.getSenha())
                  .aluno(usuario.getAluno() != null ? paraAlunoResponse(usuario.getAluno()) : null)
                  .professor(usuario.getProfessor() != null ? paraProfessorResponse(usuario.getProfessor()) : null).build();
      }

      public ProfessorResponse paraProfessorResponse(Professor professor){
          return ProfessorResponse.builder().id(professor.getId())
                  .nome(professor.getNome())
                  .dataNascimento(professor.getDataNascimento())
                  .especialidade(professor.getEspecialidade())
                  .build();
      }

      public AlunoResponse paraAlunoResponse(Aluno aluno){
          return AlunoResponse.builder().id(aluno.getId())
                  .nome(aluno.getNome())
                  .matricula(aluno.getMatricula())
                  .dataNascimento(aluno.getDataNascimento())
                  .build();
      }







}
