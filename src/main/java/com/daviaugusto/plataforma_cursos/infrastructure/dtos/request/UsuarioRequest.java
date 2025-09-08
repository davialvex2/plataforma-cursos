package com.daviaugusto.plataforma_cursos.infrastructure.dtos.request;

import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Aluno;
import com.daviaugusto.plataforma_cursos.infrastructure.entitys.Professor;
import com.daviaugusto.plataforma_cursos.infrastructure.enums.RoleEnum;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UsuarioRequest {

    private String email;
    private String senha;
    private RoleEnum role;
    private ProfessorRequest professor;
    private AlunoRequest aluno;
}
