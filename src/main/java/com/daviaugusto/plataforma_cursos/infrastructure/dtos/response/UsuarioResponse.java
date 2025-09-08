package com.daviaugusto.plataforma_cursos.infrastructure.dtos.response;

import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Aluno;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Professor;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UsuarioResponse {

    private Long id;
    private String email;
    private String senha;
    private ProfessorResponse professor;
    private AlunoResponse aluno;
}
