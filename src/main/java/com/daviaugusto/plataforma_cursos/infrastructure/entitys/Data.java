package com.daviaugusto.plataforma_cursos.infrastructure.entitys;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBanco;
    private String video_quality;
    private String status;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dados", referencedColumnName = "idBanco")
    private List<PlaybackId> playback_ids = new ArrayList<>();
    private String mp4_support;
    private String max_resolution_tier;
    private String master_access;
    private String ingest_type;
    private String id;
    private String encoding_tier;
    private String created_at;
    @Column(name = "curso_id")
    private Long curso_id;

    public Data() {
    }

    public Data(String video_quality, String status, List<PlaybackId> playback_ids, String mp4_support, String max_resolution_tier, String master_access, String ingest_type, String id, String encoding_tier, String created_at) {
        this.video_quality = video_quality;
        this.status = status;
        this.playback_ids = playback_ids;
        this.mp4_support = mp4_support;
        this.max_resolution_tier = max_resolution_tier;
        this.master_access = master_access;
        this.ingest_type = ingest_type;
        this.id = id;
        this.encoding_tier = encoding_tier;
        this.created_at = created_at;
    }

    public String getVideo_quality() {
        return video_quality;
    }

    public void setVideo_quality(String video_quality) {
        this.video_quality = video_quality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PlaybackId> getPlayback_ids() {
        return playback_ids;
    }

    public void setPlayback_ids(List<PlaybackId> playback_ids) {
        this.playback_ids = playback_ids;
    }

    public String getMp4_support() {
        return mp4_support;
    }

    public void setMp4_support(String mp4_support) {
        this.mp4_support = mp4_support;
    }

    public String getMax_resolution_tier() {
        return max_resolution_tier;
    }

    public void setMax_resolution_tier(String max_resolution_tier) {
        this.max_resolution_tier = max_resolution_tier;
    }

    public String getMaster_access() {
        return master_access;
    }

    public void setMaster_access(String master_access) {
        this.master_access = master_access;
    }

    public String getIngest_type() {
        return ingest_type;
    }

    public void setIngest_type(String ingest_type) {
        this.ingest_type = ingest_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEncoding_tier() {
        return encoding_tier;
    }

    public void setEncoding_tier(String encoding_tier) {
        this.encoding_tier = encoding_tier;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Long getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(Long curso_id) {
        this.curso_id = curso_id;
    }

    public Long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Long idBanco) {
        this.idBanco = idBanco;
    }
}
