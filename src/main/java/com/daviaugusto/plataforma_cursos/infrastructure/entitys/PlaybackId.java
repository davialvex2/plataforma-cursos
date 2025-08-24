package com.daviaugusto.plataforma_cursos.infrastructure.entitys;


import jakarta.persistence.*;

@Entity
public class PlaybackId {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBanco;
    private String policy;
    private String id;
    @JoinColumn(name = "id_dados")
    private Long id_dados;

    public PlaybackId() {
    }

    public PlaybackId(String policy, String id) {
        this.policy = policy;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public Long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Long idBanco) {
        this.idBanco = idBanco;
    }

    public Long getId_dados() {
        return id_dados;
    }

    public void setId_dados(Long id_dados) {
        this.id_dados = id_dados;
    }
}
