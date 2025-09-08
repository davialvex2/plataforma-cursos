package com.daviaugusto.plataforma_cursos.infrastructure.dtos.response;


import com.daviaugusto.plataforma_cursos.infrastructure.entitys.entidadesMux.Data;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode

public class CursoResponse {

    private Long id;
    private String nome;
    private String descricao;
    private ProfessorResponse professorResponse;
    private List<AlunoResponse> alunoResponse;


}
