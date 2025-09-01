package com.daviaugusto.plataforma_cursos.infrastructure.entitys.entidadesMux;

public class Input {

    public Input() {
    }

    public Input(String url) {
        this.url = url;
    }

    private String url;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

}
