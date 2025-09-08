package com.daviaugusto.plataforma_cursos.infrastructure.exceptions;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException(String msg, Throwable throwable) {
        super(msg);
    }
}
