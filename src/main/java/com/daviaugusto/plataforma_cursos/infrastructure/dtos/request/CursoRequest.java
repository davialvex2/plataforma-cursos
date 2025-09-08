package com.daviaugusto.plataforma_cursos.infrastructure.dtos.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode

public class CursoRequest {

    private String nome;
    private String descricao;

}
