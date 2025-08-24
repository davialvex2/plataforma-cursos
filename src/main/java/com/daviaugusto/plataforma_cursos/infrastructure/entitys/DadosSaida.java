package com.daviaugusto.plataforma_cursos.infrastructure.entitys;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


public class DadosSaida {


    private Data data;

    public DadosSaida(){
    }

    public DadosSaida(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
