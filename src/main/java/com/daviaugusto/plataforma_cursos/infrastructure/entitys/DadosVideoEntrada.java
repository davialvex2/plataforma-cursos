package com.daviaugusto.plataforma_cursos.infrastructure.entitys;


import java.util.List;

public class DadosVideoEntrada {

    private List<Input> inputs;
    private List<String> playback_policies;
    private String video_quality;

    public DadosVideoEntrada() {
    }

    public DadosVideoEntrada(List<Input> inputs, List<String> playback_policies, String video_quality) {
        this.inputs = inputs;
        this.playback_policies = playback_policies;
        this.video_quality = video_quality;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setUrl(List<Input> inputs) {
        this.inputs = inputs;
    }

    public List<String> getPlayback_policies() {
        return playback_policies;
    }

    public void setPlayback_policies(List<String> playback_policies) {
        this.playback_policies = playback_policies;
    }

    public String getVideo_quality() {
        return video_quality;
    }

    public void setVideo_quality(String video_quality) {
        this.video_quality = video_quality;
    }
}
