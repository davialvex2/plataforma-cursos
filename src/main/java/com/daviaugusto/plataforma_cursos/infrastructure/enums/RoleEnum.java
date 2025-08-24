package com.daviaugusto.plataforma_cursos.infrastructure.enums;

public enum RoleEnum {
    ADMIN("ADMIN"),
    PROFESSOR("PROFESSOR"),
    ALUNO("ALUNO");

    private String role;

    RoleEnum(){
    }

    RoleEnum(String role){
        this.role = role;
    }


    String getRole(){
        return this.role;
    }

}
